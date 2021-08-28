package com.example.weather.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Weather {


    @SerializedName("main")
    @Expose
    private String main;
    private String description;

    public Weather(String main, String description) {
        this.main = main;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getmain() {
        return main;
    }
}
