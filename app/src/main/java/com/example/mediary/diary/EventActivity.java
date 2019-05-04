package com.example.mediary.diary;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mediary.R;
import com.example.mediary.services.ServerRestClientManager;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class EventActivity extends AppCompatActivity {
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Intent intent = getIntent();
        String message = intent.getStringExtra("title");
        name = (TextView) findViewById(R.id.name);
//        getFromAsyncHttpClient();
        name.setText(message);

        FloatingActionButton floatingActionButton =
                (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getBaseContext(), DynamicFragment.class);
//                startActivity(intent);

//                submitFormToAsyncHttpClient();

                //                Intent intent = new Intent(getBaseContext(), DynamicFragment.class);
//                startActivity(intent);
            }
        });
    }

    private void getFromAsyncHttpClient() {

        RequestParams getData = new RequestParams();

        getData.put("firstNames", "Dave");
        getData.put("lastName", "Doe");
        getData.put("gender", "M");
        getData.put("email", "davedoe@example.com");

        ServerRestClientManager.get("patients", getData, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                Log.e("FAILURE:", String.valueOf(response));
                if (statusCode == 204) { //Menu up to date
                    Log.d("success", ",mood up-to-date!");

                    Toast.makeText(EventActivity.this, "Complete...", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("success", "got mood");

                    Toast.makeText(EventActivity.this, "Complete...", Toast.LENGTH_SHORT).show();
//
//                    try {
////                        JSONObject u = response.getJSONObject("moods");
////                        int slp = u.getInt("sleeping");
//                    } catch (JSONException e) {
//                        Log.e("FAILURE:", e.getMessage());
//                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String error, Throwable throwable) {
                Log.e("FAILURE:", error);
                Toast.makeText(EventActivity.this, "Server not available...", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void submitFormToAsyncHttpClient() {
        RequestParams postData = new RequestParams();

        postData.put("patientId", "/patients/3");
        postData.put("title", "Breakfast");
        postData.put("body", "It's delicious!");
        postData.put("dateTime", "2019-05-04T07:30:00.000Z");

        ServerRestClientManager.post("events", postData, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (statusCode == 204) { //Menu up to date
                    Log.d("success", ",mood up-to-date!");

                    Toast.makeText(EventActivity.this, "Complete...", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("success", "got events");
                    Toast.makeText(EventActivity.this, "Complete...", Toast.LENGTH_SHORT).show();

                    finish();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String error, Throwable throwable) {
                Log.e("FAILURE:", error);
                Toast.makeText(EventActivity.this, "Server not available...", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                Log.e("FAILURE:", statusCode+"");
                Toast.makeText(EventActivity.this, "Server not available...", Toast.LENGTH_SHORT).show();
//                AsyncHttpClient.log.w(LOG_TAG, "onFailure(int, Header[], Throwable, JSONObject) was not overriden, but callback was received", throwable);

            }
        });
    }
}
