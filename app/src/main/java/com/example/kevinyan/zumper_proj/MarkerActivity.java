package com.example.kevinyan.zumper_proj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MarkerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marker);

        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("id");

        Log.d("bundle id", id + "test");
    }
}
