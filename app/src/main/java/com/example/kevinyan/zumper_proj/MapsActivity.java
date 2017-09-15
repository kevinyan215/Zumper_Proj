package com.example.kevinyan.zumper_proj;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.kevinyan.zumper_proj.pojo.PlaceResponse;
import com.example.kevinyan.zumper_proj.model.PlacesAPI;
import com.example.kevinyan.zumper_proj.model.RetrofitHelper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener{

    private GoogleMap mMap;
    private PlacesAPI service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        RetrofitHelper.init();
        service = RetrofitHelper.getService();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.7723603,-122.4298578),15.0f));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.7723603,-122.4298578),17.0f));

        displayPlaces(getLocation());

        mMap.setOnMarkerClickListener(this);

        mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
//                mMap.clear();
                Log.d("Camera", "move");
//                displayPlaces(getLocation());
            }
        });
    }

    public String getLocation(){
        return mMap.getCameraPosition().target.latitude + "," + mMap.getCameraPosition().target.longitude;
    }

    public void displayPlaces(String location){
        Call<PlaceResponse> call = service.getPicture(location);
        call.enqueue(new Callback<PlaceResponse>() {
            @Override
            public void onResponse(Call<PlaceResponse> call, Response<PlaceResponse> response) {
                try{

                    for(int i = 0; i < response.body().getResults().size(); i++){

                        String id = response.body().getResults().get(i).getId();
                        String name = response.body().getResults().get(i).getName();
                        String address = response.body().getResults().get(i).getVicinity();
                        double rating = response.body().getResults().get(i).getRating();
                        double lat = response.body().getResults().get(i).getGeometry().getLocation().getLat();
                        double lng = response.body().getResults().get(i).getGeometry().getLocation().getLng();

                        List.names.add(name);
                        List.rating.add(rating);

                        Log.d("response", id);
                        Log.d("response", name + "");
                        Log.d("response", address);
                        Log.d("response", rating + "");
                        Log.d("response", lat + ", " + lng);

                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(new LatLng(lat,lng));
                        markerOptions.title(name + " " + address + " " + rating);
                        Marker marker = mMap.addMarker(markerOptions);
                        marker.setTag(id);

                    }
                }catch(Exception e){
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<PlaceResponse> call, Throwable t) {
                Toast.makeText(MapsActivity.this, "API Failure", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void displayList(View view){
        Intent startListIntent = new Intent(this, List.class);
        startActivity(startListIntent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int action = event.getAction();
        Log.d("gesture", action +  "");
        return true;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        Log.d("marker id", marker.getTag() + "");

        Intent markerIntent = new Intent(this, MarkerActivity.class);
        markerIntent.putExtra("id", marker.getTag() + "");
        startActivity(markerIntent);
        return true;
    }
}
