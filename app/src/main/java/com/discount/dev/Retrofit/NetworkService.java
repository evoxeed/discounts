package com.discount.dev.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static Retrofit ourInstance;

    private static final String BASE_API_URl = "https://discount.maffinca.com/api/";

    public static Retrofit getInstance() {
        if(ourInstance == null) {
            ourInstance = new Retrofit.Builder()
                    .baseUrl(BASE_API_URl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return ourInstance;
    }

    public static JsonApi getService() {
        return getInstance().create(JsonApi.class);
    }
}