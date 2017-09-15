package com.example.kevinyan.zumper_proj.pojo;

import java.util.ArrayList;

/**
 * Created by kevinyan on 9/10/17.
 */

public class Result {
    private String name;
    private double rating;
    private ArrayList<String> types = new ArrayList<>();
    private String vicinity;
    private Geometry geometry;

    public String getName(){
        return name;
    }
    public double getRating() {
        return rating;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public String getVicinity() {
        return vicinity;
    }

    public Geometry getGeometry(){
        return geometry;
    }

}
