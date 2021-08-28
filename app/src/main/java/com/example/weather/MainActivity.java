package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData("Visakhapatnam");
    }

    public void getData(String Location){

    }
}