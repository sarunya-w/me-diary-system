package com.example.mediary.diary.fragments;

import android.app.Activity;
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
import com.example.mediary.model.User;
import com.example.mediary.services.IClientServices;
import com.example.mediary.services.IOnNetworkCallbackListener;
import com.example.mediary.services.NetworkConnectionManager;
import com.example.mediary.services.ServerRestClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import cz.msebera.android.httpclient.Header;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MoodFragment extends Fragment {

    private Activity currentActivity;
    private EditText sleeping;
    private EditText weight;
//    private EditText address;
//    private EditText name;
//    private EditText manufacturer;
//    private EditText location;
//    private EditText type;
//    private EditText deviceID;
//    mood" : {
//            "moodiness": 1,
//            "anxiety": 0,
//            "irritability": 0,
//            "sleeping": 8,
//            "weight": 50,
//            "created": "2019-05-01T18:25:43.511Z",
//            "updated": null
//}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        currentActivity = getActivity();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mood, container, false);
        String[] values = new String[] { "Android List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View"
        };
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

                HashMap<String, String> mood_params = new HashMap<>();

                RequestParams postData = new RequestParams();

                mood_params.put("sleeping", sleeping.getText().toString());
                mood_params.put("weight", weight.getText().toString());
                postData.put("mood", mood_params);

//                user_params.put("email", "jjj");
//                user_params.put("pin", "kkkk");

//                JSONObject postData = new JSONObject();
//                try {
//                    postData.put("manufacturer", manufacturer.getText().toString());
//                    postData.put("location", location.getText().toString());
//                    postData.put("type", type.getText().toString());
//                    postData.put("deviceID", deviceID.getText().toString());

                    ServerRestClient.post("mood", postData, new JsonHttpResponseHandler() {
                      @Override
                      public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                          if(statusCode == 204) { //Menu up to date
                              Log.d("success", "menu up-to-date!");
            //                  swipeContainer.setRefreshing(false);
                          } else {

                                  Toast.makeText(getContext(), "completed", Toast.LENGTH_SHORT).show();

                          }
                      }

                      @Override
                      public void onFailure(int statusCode, Header[] headers, String error, Throwable throwable){
                          Log.e("FAILURE:", error);
            //              Toast.makeText(context, "Server not available...", Toast.LENGTH_SHORT).show();
            //              swipeContainer.setRefreshing(false);
                      }
                  });

//                submit("","");
//                ServerRestClient request = new ServerRestClient();
//                request.execute();
            }
        });

        this.sleeping = (EditText) view.findViewById(R.id.sleeping);
        this.weight = (EditText) view.findViewById(R.id.weight);

        return view;
    }


  private void submit(String email, String password) {
      HashMap<String, String> user_params = new HashMap<>();
      RequestParams user = new RequestParams();

      user_params.put("email", "jjj");
      user_params.put("pin", "kkkk");

      user.put("user", user_params);

      ServerRestClient.get("login", user, new JsonHttpResponseHandler() {
          @Override
          public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
              if(statusCode == 204) { //Menu up to date
                  Log.d("success", "menu up-to-date!");
//                  swipeContainer.setRefreshing(false);

                  Toast.makeText(currentActivity.getApplicationContext(), "Complete...1", Toast.LENGTH_SHORT).show();
              } else {
                  Log.d("success", "got menu");

                  Toast.makeText(currentActivity.getApplicationContext(), "Complete...", Toast.LENGTH_SHORT).show();
//                  try {
//                      JSONArray menu = (JSONArray) response.get("menu");
//                      HashMap<Integer, Product> new_menu = new HashMap<>();
//
//                      for (int i = 0; i < menu.length(); ++i) {
//                          Product p = new Product(menu.getJSONObject(i));
//                          new_menu.put(p.getId(), p);
//                      }
//
//                      list.setProducts(new_menu);
//                      refreshAdapterData();
//
//                      //Save menu to memory
//                      CustomLocalStorage.set(currentActivity, "menu", SerializeToString.toString(list));
//                      String menu_version = response.getString("version");
//                      CustomLocalStorage.set(currentActivity, "menu_version", menu_version);
//                  } catch (JSONException e) {
//                      //problem with server. probably.
//                  } catch (IOException io) {
//                      //error serializing menu object
//                      Log.e("Serialize", "Couldn't save Menu to memory");
//                  } finally {
//                      swipeContainer.setRefreshing(false);
//                  }
              }
          }

          @Override
          public void onFailure(int statusCode, Header[] headers, String error, Throwable throwable){
              Log.e("FAILURE:", error);
//              Toast.makeText(context, "Server not available...", Toast.LENGTH_SHORT).show();
//              swipeContainer.setRefreshing(false);
          }
      });

//      IOnNetworkCallbackListener networkCallbackListener = new IOnNetworkCallbackListener() {
//          @Override
//          public void onResponse(User user, Retrofit retrofit) {
//              //200
//              Toast.makeText(currentActivity.getApplicationContext(), "Server error...", Toast.LENGTH_SHORT).show();
//////
//          }
//
//          @Override
//          public void onBodyError(ResponseBody responseBodyError) {
//              //404 (error not null)
//
//          }
//
//          @Override
//          public void onBodyErrorIsNull() {
//              //404 (error is null)
//
//          }
//
//          @Override
//          public void onFailure(Throwable t) {
//              //fail any course
//
//          }
//      };
//      new NetworkConnectionManager().callServer(networkCallbackListener, "");

      //Obtain an instance of Retrofit by calling the static method.
//      Retrofit retrofit = ServerRestClient.getRetrofitClient();
////        /*
////        The main purpose of Retrofit is to create HTTP calls from the Java interface based on the annotation associated with each method. This is achieved by just passing the interface class as parameter to the create method
////        */
//      IClientServices services = retrofit.create(IClientServices.class);
//        /*
//        Invoke the method corresponding to the HTTP request which will return a Call object. This Call object will used to send the actual network request with the specified parameters
//        */
//      Call<String> call = services.goLogin();
//        /*
//        This is the line which actually sends a network request. Calling enqueue() executes a call asynchronously. It has two callback listeners which will invoked on the main thread
//        */
//      call.enqueue(new Callback() {
//          @Override
//          public void onResponse(Call call, Response response) {
//              System.out.println(response);
//              /*This is the success callback. Though the response type is JSON, with Retrofit we get the response in the form of WResponse POJO class
//               */
//              if (response.body() != null) {
//                  Toast.makeText(currentActivity.getApplicationContext(), "Server error...", Toast.LENGTH_SHORT).show();
//////
//              }
//          }
//          @Override
//          public void onFailure(Call call, Throwable t) {
//                /*
//                Error callback
//                */
//          }
//      });
  }
}
