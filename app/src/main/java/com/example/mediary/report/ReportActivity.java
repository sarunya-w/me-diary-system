package com.example.mediary.report;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mediary.CustomLocalStorage;
import com.example.mediary.R;
import com.example.mediary.SerializeToString;
import com.example.mediary.services.ServerRestClientManager;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class ReportActivity extends AppCompatActivity {
    public static final String FRAGMENT_PDF_RENDERER_BASIC = "pdf_renderer_basic";
    private Activity currentActivity;

    String[] list = new String[]{"Report_January_2019",
            "Report_Febuary_2019",
            "Report_March_2019",
            "Report_April_2019"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        currentActivity = this;
        fetchProductsAsync();

        ListView listView = (ListView) findViewById(R.id.data_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ReportActivity.this, list[position], Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(currentActivity, PdfViewerActivity.class);
                startActivity(intent);

            }
        });
    }

    private void  fetchProductsAsync() {
        HashMap<String, String> mood_params = new HashMap<>();

        RequestParams postData = new RequestParams();

        postData.put("firstNames", "Dave");
        postData.put("lastName", "Doe");
        postData.put("gender", "M");
        postData.put("email", "davedoe@example.com");

        ServerRestClientManager.get("", postData, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if(statusCode == 204) { //Menu up to date

                } else {
//                    try {
//
////                        Toast.makeText(currentActivity.getApplicationContext(), "Complete...", Toast.LENGTH_SHORT).show();
////                        response.get("detail");
//                        JSONArray report = (JSONArray)response.get("detail");
//////                        int aa = a.length;
//////////                        HashMap<Integer, Product> new_menu = new HashMap<>();
////                        Toast.makeText(currentActivity.getApplicationContext(), "xxxx", Toast.LENGTH_SHORT).show();
//                        ArrayList<String> a = new ArrayList<>();

//                        JSONArray menu = (JSONArray) response.get("detail");
//

//                        for (int i = 0; i < report.length(); ++i) {
//                                a.add(report.getJSONObject(i).toString());
//////
//                        }
////
//                    } catch (JSONException e) {
//                        //problem with server. probably.
//                        Toast.makeText(currentActivity.getApplicationContext(), "Server error...", Toast.LENGTH_SHORT).show();
//                    }

//                        HashMap<Integer, Product> new_menu = new HashMap<>();
//
//                        for (int i = 0; i < menu.length(); ++i) {
//                            Product p = new Product(menu.getJSONObject(i));
//                            new_menu.put(p.getId(), p);
//                        }
//
//                        list.setProducts(new_menu);
//                        refreshAdapterData();
//
//                        //Save menu to memory
//                        CustomLocalStorage.set(currentActivity, "menu", SerializeToString.toString(list));
//                        String menu_version = response.getString("version");
//                        CustomLocalStorage.set(currentActivity, "menu_version", menu_version);

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String error, Throwable throwable){
            }
        });
    }

}
