package com.example.weather.Model;

public class Coordinates extends WeatherInfo{
    private int lon;
    private int lat;

    public Coordinates(int lon, int lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public int getLon() {
        return lon;
    }

    public int getLat() {
        return lat;
    }
}
