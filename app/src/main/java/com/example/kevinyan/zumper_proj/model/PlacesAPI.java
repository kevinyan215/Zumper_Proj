package com.example.kevinyan.zumper_proj.model;

import com.example.kevinyan.zumper_proj.pojo.PlaceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kevinyan on 9/10/17.
 */

public interface PlacesAPI {

//    @GET("api/place/nearbysearch/json?location=37.773972,-122.431297&radius=500&type=restaurant&key=AIzaSyB-bpw0ollWA5AKpT11Y2CL2qPFs4kC_dk")
    @GET("api/place/nearbysearch/json?radius=300&type=restaurant&key=AIzaSyB-bpw0ollWA5AKpT11Y2CL2qPFs4kC_dk")
    Call<PlaceResponse> getPicture(@Query("location") String location);
}
