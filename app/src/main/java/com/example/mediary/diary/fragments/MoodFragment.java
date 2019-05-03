package com.example.mediary.diary.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mediary.R;
import com.example.mediary.services.IClientServices;
import com.example.mediary.services.ServerRestClientManager;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import cz.msebera.android.httpclient.Header;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MoodFragment extends Fragment {

    private Activity currentActivity;
    private EditText sleeping;
    private EditText weight;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        currentActivity = getActivity();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mood, container, false);

        TextView vDate = (TextView) view.findViewById(R.id.txtDate);

        String date = "";
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            date = bundle.getString("DATE");
            vDate.setText(date);
        }

//        ListView listView = (ListView)view.findViewById(R.id.data_list);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);
//        listView.setAdapter(adapter);

        FloatingActionButton floatingActionButton =
                (FloatingActionButton) view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getFromAsyncHttpClient();
//                summitFormToAsyncHttpClient(sleeping.getText().toString(), weight.getText().toString());
            }
        });
//
        this.sleeping = (EditText) view.findViewById(R.id.sleeping);
        this.weight = (EditText) view.findViewById(R.id.weight);

        return view;
    }

    private void getFromAsyncHttpClient() {

        RequestParams getData = new RequestParams();

        getData.put("firstNames", "Dave");
        getData.put("lastName", "Doe");
        getData.put("gender", "M");
        getData.put("email", "davedoe@example.com");

        ServerRestClientManager.get("", getData, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (statusCode == 204) { //Menu up to date
                    Log.d("success", ",mood up-to-date!");

                    Toast.makeText(currentActivity.getApplicationContext(), "Complete...", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("success", "got mood");

                    Toast.makeText(currentActivity.getApplicationContext(), "Complete...", Toast.LENGTH_SHORT).show();

                    try {
                        JSONObject u = response.getJSONObject("moods");
                        int slp = u.getInt("sleeping");
                    } catch (JSONException e) {
                        Log.e("FAILURE:", e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String error, Throwable throwable) {
                Log.e("FAILURE:", error);
                Toast.makeText(currentActivity.getApplicationContext(), "Server not available...", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void summitFormToAsyncHttpClient(String sleeping, String weight) {
        HashMap<String, String> mood_params = new HashMap<>();

        RequestParams postData = new RequestParams();

        mood_params.put("sleeping", sleeping);
        mood_params.put("weight", weight);
        postData.put("mood", mood_params);

        ServerRestClientManager.post("mood", postData, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (statusCode == 204) { //Menu up to date
                    Log.d("success", ",mood up-to-date!");

                    Toast.makeText(currentActivity.getApplicationContext(), "Complete...", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("success", "got mood");

                    Toast.makeText(currentActivity.getApplicationContext(), "Complete...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String error, Throwable throwable) {
                Log.e("FAILURE:", error);
                Toast.makeText(currentActivity.getApplicationContext(), "Server not available...", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void summitFormToRetrofit() {

        //Obtain an instance of Retrofit by calling the static method.
        Retrofit retrofit = ServerRestClientManager.getRetrofitClient();
        /*
        The main purpose of Retrofit is to create HTTP calls from the Java interface based on the annotation associated with each method. This is achieved by just passing the interface class as parameter to the create method
        */
        IClientServices services = retrofit.create(IClientServices.class);
        /*
        Invoke the method corresponding to the HTTP request which will return a Call object. This Call object will used to send the actual network request with the specified parameters
        */
        Call<String> call = services.test();
        /*
        This is the line which actually sends a network request. Calling enqueue() executes a call asynchronously. It has two callback listeners which will invoked on the main thread
        */
        call.enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) {

                System.out.println(response);
//                User user = response.body();
                if (response.body() != null) {
                    // 200

                    Toast.makeText(currentActivity.getApplicationContext(), "Completed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                    /*
                    Error callback
                    */
            }

//            @Override
//            public void onBodyError(ResponseBody responseBodyError) {
//                //404 (error not null)
//
//            }
//
//            @Override
//            public void onBodyErrorIsNull() {
//                //404 (error is null)
//
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                //fail any course
//
//            }
        });
    }
}
