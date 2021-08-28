package com.example.weather.Model;

public class Main extends WeatherInfo{
    private int temp;
    private int pressure;
    private int humidity;
    private int temp_min;
    private int temp_max;

    public Main(int temp, int pressure, int humidity, int temp_min, int temp_max) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
    }

    public int getTemp() {
        return temp;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getTemp_min() {
        return temp_min;
    }

    public int getTemp_max() {
        return temp_max;
    }
}
