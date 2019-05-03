package com.example.mediary.services;

import com.example.mediary.model.User;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkConnectionManager {

    private static final String BASE_URL = "http://10.0.2.2:3000";

    public void callServer(final IOnNetworkCallbackListener listener, String username){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IClientServices service = retrofit.create(IClientServices.class);

        Call<String> call = service.goLogin();
        try {
            call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        call.enqueue(this);
//        Call call = git.getUser(username);

//        call.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                User user = response.body();
//
//                if (user == null) {
//                    //404 or the response cannot be converted to User.
//                    ResponseBody responseBody = response.errorBody();
//                    if (responseBody != null) {
//                        listener.onBodyError(responseBody);
//                    } else {
//                        listener.onBodyErrorIsNull();
//                    }
//                } else {
//                    //200
////                    listener.onResponse(user, retrofit);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//
//            }
//
//        });

    }
}
