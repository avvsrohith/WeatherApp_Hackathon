package com.example.weather.Model;

public class Wind extends WeatherInfo{
    private int speed;

    public Wind(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }
}
