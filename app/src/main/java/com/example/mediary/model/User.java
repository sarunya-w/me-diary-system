package com.example.mediary.model;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.example.mediary.CustomLocalStorage;
import com.google.gson.annotations.Expose;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;


public class User implements Serializable{

    @Expose
    String name;
    @Expose
    String blog;
    @Expose
    String company;

    public String getName() {
        return name;
    }

    public String getBlog() {
        return blog;
    }

    public String getCompany() {
        return company;
    }
}
