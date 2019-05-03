package com.example.mediary.services;

import com.loopj.android.http.*;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerRestClient  {

    private static final String BASE_URL = "http://10.0.2.2:3000/";
    private static final String BASE_URL2 = "http://10.0.2.2:3000";
    //private static final String BASE_URL = "http://192.168.2.117:5000/";
    //private static final String BASE_URL = "http://172.30.11.233:5000/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    public static Retrofit retrofit;
    /*
    This public static method will return Retrofit client
    anywhere in the appplication
    */
    public static Retrofit getRetrofitClient() {
//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        //If condition to ensure we don't create multiple retrofit instances in a single application
        if (retrofit == null) {
            //Defining the Retrofit using Builder
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL2) //This is the only mandatory call on Builder object.
                    .addConverterFactory(GsonConverterFactory.create()) // Convertor library used to convert response into POJO
//                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }
}
