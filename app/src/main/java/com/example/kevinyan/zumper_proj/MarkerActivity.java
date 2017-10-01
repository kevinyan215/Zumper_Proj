package com.example.kevinyan.zumper_proj;

import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kevinyan.zumper_proj.model.PlacesAPI;
import com.example.kevinyan.zumper_proj.model.RetrofitHelper;
import com.example.kevinyan.zumper_proj.pojo.PlaceDetailResponse;
import com.example.kevinyan.zumper_proj.pojo.PlaceDetailResponse;
import com.example.kevinyan.zumper_proj.pojo.Result;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MarkerActivity extends AppCompatActivity{

    private PlacesAPI service;
    private ArrayList<String> reviews;
    private Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marker);

        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("id");

        service = RetrofitHelper.getService();
        Call<PlaceDetailResponse> call = service.getPlaceDetails(id);
        call.enqueue(new Callback<PlaceDetailResponse>() {
            @Override
            public void onResponse(Call<PlaceDetailResponse> call, Response<PlaceDetailResponse> response) {
                result = response.body().getResult();
                Log.d("response", "response");
                ListView lv = (ListView) findViewById(R.id.lv);
                lv.setAdapter(new CustomAdapter());
            }

            @Override
            public void onFailure(Call<PlaceDetailResponse> call, Throwable t) {

            }
        });
        Log.d("bundle id", id + "");

//        ListView lv = (ListView) findViewById(R.id.lv);
//        lv.setAdapter(new CustomAdapter());
    }

    class CustomExpandableListAdapter extends BaseExpandableListAdapter {

        @Override
        public int getGroupCount() {
            return 0;
        }

        @Override
        public int getChildrenCount(int i) {
            return 0;
        }

        @Override
        public Object getGroup(int i) {
            return null;
        }

        @Override
        public Object getChild(int i, int i1) {
            return null;
        }

        @Override
        public long getGroupId(int i) {
            return 0;
        }

        @Override
        public long getChildId(int i, int i1) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
            return null;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
            return null;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return false;
        }
    }
    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.custom_list_place_details, null);

            TextView name = view.findViewById(R.id.name);
            name.setText(result.getName());

            TextView rating = view.findViewById(R.id.rating);
            rating.setText(result.getRating() + "");

            TextView review = view.findViewById(R.id.review);
            review.setText(result.getReviews().get(0).getText());
            Log.d("view", "view");
            return view;

        }
    }
}
