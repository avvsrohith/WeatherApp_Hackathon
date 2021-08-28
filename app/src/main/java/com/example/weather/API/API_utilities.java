package com.example.weather.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API_utilities {

    public static final String BASE_URL="https://api.openweathermap.org/data/2.5/weather/";
    public static final String API_KEY="30ec03122adebc333814ac38269399a4";

    public static Retrofit retrofit=null;

    public static API_call getAPIinterface(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder().
                    baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit.create(API_call.class);
    }


}
