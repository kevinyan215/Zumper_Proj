package com.example.kevinyan.zumper_proj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class List extends AppCompatActivity {

    public static ArrayList<String> names = new ArrayList<>();
    public static ArrayList<Double> rating = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(new CustomAdapter());
    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return names.size();
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
            view = getLayoutInflater().inflate(R.layout.custom_list_layout, null);

            TextView placeName = view.findViewById(R.id.place_name);
            TextView placeAddress = view.findViewById(R.id.place_address);

            placeName.setText(names.get(i));
            placeAddress.setText(rating.get(i) + "");
            return view;
        }
    }


}
