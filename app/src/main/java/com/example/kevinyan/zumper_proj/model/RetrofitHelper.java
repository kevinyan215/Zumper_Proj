package com.example.kevinyan.zumper_proj.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kevinyan on 9/14/17.
 */

public class RetrofitHelper {
    private static String BASE_URL = "https://maps.googleapis.com/maps/";
    private static Retrofit retrofit;

    public static void init(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static PlacesAPI getService(){
        return retrofit.create(PlacesAPI.class);
    }
}
