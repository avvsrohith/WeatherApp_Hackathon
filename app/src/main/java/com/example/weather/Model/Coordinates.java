package com.example.weather.Model;

public class Coordinates {
    private float lon;
    private float lat;

    public Coordinates(float lon, float lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public float getLat() {
        return lat;
    }
}
