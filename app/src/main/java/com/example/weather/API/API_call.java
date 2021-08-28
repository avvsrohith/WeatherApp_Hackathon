package com.example.weather.API;

import static com.example.weather.API.API_utilities.API_KEY;
import static com.example.weather.API.API_utilities.BASE_URL;

import com.example.weather.Model.WeatherInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API_call {

    @GET(BASE_URL)
    Call<WeatherInfo> getWeather(
        @Query("appid") String apikey,
        @Query("q") String Location
    );
}
