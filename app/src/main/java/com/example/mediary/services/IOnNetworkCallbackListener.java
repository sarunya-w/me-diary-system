package com.example.mediary.services;

import com.example.mediary.model.User;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public interface IOnNetworkCallbackListener {
    public void onResponse(User user, Retrofit retrofit);
    public void onBodyError(ResponseBody responseBodyError);
    public void onBodyErrorIsNull();
    public void onFailure(Throwable t);
}
