package com.example.mediary.diary.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mediary.R;
import com.example.mediary.diary.EventActivity;
import com.example.mediary.report.PdfViewerActivity;
import com.example.mediary.report.ReportActivity;
import com.example.mediary.services.ServerRestClientManager;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

public class EventFragment extends Fragment {

    private Activity currentActivity;
    ArrayList<String> list;
    private ProgressBar pg;
    private ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        currentActivity = getActivity();

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_event, container, false);
        View view = inflater.inflate(R.layout.fragment_event, container, false);

        pg = (ProgressBar) view.findViewById(R.id.progress);
        TextView vDate = (TextView) view.findViewById(R.id.txtDate);
        listView = (ListView)view.findViewById(R.id.data_list);

        String date = "";
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            date = bundle.getString("DATE");
            vDate.setText(date);
        }

        list = new ArrayList<String>();
//        getData();
        getFromAsyncHttpClient();



        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
        Log.d("success1", ",event up-to-date!");

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), EventActivity.class);

                intent.putExtra("title", list.get(position));
                startActivity(intent);
            }
        });


        FloatingActionButton floatingActionButton =
                (FloatingActionButton) view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), EventActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void getFromAsyncHttpClient() {

        RequestParams getData = new RequestParams();

        getData.put("patientId", 2);

        ServerRestClientManager.get("events/search/findByPatientId", getData, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (statusCode == 204) { //Menu up to date
                    Log.d("success", ",event up-to-date!");

//                    Toast.makeText(currentActivity.getApplicationContext(), "Complete...1", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("success", "got event");

//                    Toast.makeText(currentActivity.getApplicationContext(), "Complete...2", Toast.LENGTH_SHORT).show();


                    try {
                        JSONObject tmp = response.getJSONObject("_embedded");
                        JSONArray events = (JSONArray) tmp.get("events");
                        for(int i = 0; i < events.length(); i++){
                            JSONObject e = (JSONObject) events.get(i);
                            list.add(e.get("title").toString() + "  ("+ convertDatetime(e.get("dateTime").toString()) + ")");
                        }

                        EventFragment.this.getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listView.setAdapter(adapter);
                            }
                        });

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

    void getData()
    {
        list.add("ทานข้าวเช้าจ้าาา  (08:00)");
        list.add("ไปโรงเรียน  (08.25)");
        list.add("ทะเลาะกับเพื่อน  (14:10)");
        list.add("ทำเงินหาย  (17:10)");
    }

    public String convertDatetime(String dateFrom){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date d = null;
        try {
            d = sdf.parse(dateFrom);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return output.format(d);
    }

}
