package com.discount.dev;

import android.app.Application;

import com.discount.dev.Retrofit.JsonApi;
import com.discount.dev.Retrofit.NetworkService;

import retrofit2.Retrofit;

public class App extends Application {

    private static Application instance;
    JsonApi api;

    public static Application getInstance() {
        if (instance == null) {
            throw new RuntimeException("App is not init");
        }

        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        Retrofit retrofit = NetworkService.getInstance();
        api = retrofit.create(JsonApi.class);
    }
}
