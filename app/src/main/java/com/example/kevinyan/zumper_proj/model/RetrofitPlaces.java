package com.example.kevinyan.zumper_proj.model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitPlaces {

    @GET("api/place/nearbysearch/json?location=37.773972,-122.431297&radius=500&type=restaurant&key=AIzaSyB-bpw0ollWA5AKpT11Y2CL2qPFs4kC_dk")
    Call<PlaceResponse> getPlace();
}
