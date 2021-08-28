package com.example.weather.Model;

import java.util.ArrayList;

public class WeatherInfo {

    private int id;
    private String name;
    private ArrayList<Weather> weather;
    private Wind wind;
    private Main main;
    private Coordinates coord;

    public WeatherInfo(int id, String name, ArrayList<Weather> weather, Wind wind, Main main, Coordinates coord) {
        this.id = id;
        this.name = name;
        this.weather = weather;
        this.wind = wind;
        this.main = main;
        this.coord = coord;
    }
    public WeatherInfo(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public Wind getWind() {
        return wind;
    }

    public Main getMain() {
        return main;
    }

    public Coordinates getCoord() {
        return coord;
    }
}
