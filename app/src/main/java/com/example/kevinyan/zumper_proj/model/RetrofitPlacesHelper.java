package com.example.kevinyan.zumper_proj.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitPlacesHelper {

    public static final String BASE_URL = "https://maps.googleapis.com/maps/";
    private static Retrofit retrofit;

    public static void init() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static RetrofitPlaces getService() {
        return retrofit.create(RetrofitPlaces.class);
    }

}
