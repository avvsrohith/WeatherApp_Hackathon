package com.example.weather;

import static com.example.weather.API.API_utilities.API_KEY;
import static com.example.weather.DisplayFragment.c1;
import static com.example.weather.DisplayFragment.c2;
import static com.example.weather.DisplayFragment.c3;
import static com.example.weather.DisplayFragment.frameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weather.API.API_utilities;
import com.example.weather.Model.Weather;
import com.example.weather.Model.WeatherInfo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String DISPLAY_TAG = "display";
    DisplayFragment displayFragment;
    private LocationManager locationManager;
    private int PERMISSION_CODE=1;
    String cityname="Not found";
    FragmentContainerView fragmentContainerView;
    TextView failtv;
    TextToSpeech textToSpeech;
    WeatherInfo info;

    Boolean dark=true;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},PERMISSION_CODE);
        }

        Location location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        cityname=getCity(location.getLongitude(),location.getLatitude());

        textToSpeech=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS){
                    int result=textToSpeech.setLanguage(Locale.getDefault());

                }
            }
        });

        bottomNavigationView=findViewById(R.id.bottomtabbar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.Mode:
                        darkmode();
                        return true;
                    case R.id.tts:
                            textToSpeech.speak(speak(),TextToSpeech.QUEUE_FLUSH,null);
                        return true;
                    case R.id.home:

                        return true;
                }
                return false;
            }
        });



        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentContainerView=findViewById(R.id.fragmentContainerView);
        displayFragment=(DisplayFragment) fragmentManager.findFragmentByTag(DISPLAY_TAG);
        if(displayFragment==null){
            displayFragment=new DisplayFragment();
            fragmentManager.beginTransaction().add(R.id.fragmentContainerView,displayFragment,DISPLAY_TAG).commit();
        }

        getData(cityname);

    }

    private String getCity(double longitude,double latitude){
        String cityname="City not found";
        Geocoder geocoder=new Geocoder(getBaseContext(), Locale.getDefault());
        try{
            List<Address> addressList=geocoder.getFromLocation(latitude,longitude,10);
            for(Address address:addressList){
                if(address!=null){
                    String city=address.getLocality();
                    if(city!=null && !city.equals("")){
                        cityname=city;
                    }
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return cityname;
    }

    public void getData(String Location){
        API_utilities.getAPIinterface().getWeatherData(API_KEY,Location)
                .enqueue(new Callback<WeatherInfo>() {
                    @Override
                    public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
                        if (response.body() == null) {
                            displayFragment.setCity(cityname);

                        } else{
                             info= response.body();
                        displayFragment.setData(info.getWeather().get(0).getmain(),info.getWind().getSpeed(),info.getMain().getHumidity(),info.getMain().getPressure(),info.getMain().getTemp(),info.getMain().getTemp_max(),info.getMain().getTemp_min(),info.getName(),info.getCoord().getLon(),info.getCoord().getLat());
                    }
                    }

                    @Override
                    public void onFailure(Call<WeatherInfo> call, Throwable t) {

                    }
                });
    }

    public String speak(){
        if(info==null){
            return "No data";
        }
        else{
            return "Location"+"\n"+info.getName()+"\n"+"weather"+"\n"+info.getWeather().get(0).getmain()+"\n"+"Wind speed"+"\n"+info.getWind().getSpeed()+"\n"+"Humidity"+"\n"+info.getMain().getHumidity()+"\n"
                    +"Pressure"+"\n"+info.getMain().getPressure()+"\n"+"Temperature"+"\n"+info.getMain().getTemp()+"\n"+"Minimum temperature"+"\n"+info.getMain().getTemp_min()+"\n"+"Maximum temperature"+"\n"+info.getMain().getTemp_max()+"\n"+"Longitude"+"\n"+info.getCoord().getLon()+"\n"+"Latitude"+"\n"+info.getCoord().getLat();
        }
    }

    @Override
    protected void onDestroy() {
        if(textToSpeech!=null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }

    void darkmode(){
        if(dark){
            c1.setBackgroundResource(R.drawable.darkcardbg);
            c2.setBackgroundResource(R.drawable.darkcardbg);
            c3.setBackgroundResource(R.drawable.darkcardbg);
            frameLayout.setBackgroundColor(Color.parseColor("#202125"));
            bottomNavigationView.setItemBackgroundResource(R.drawable.grey_bg);
            dark=false;
        }
        else
        {
            c1.setBackgroundResource(R.drawable.lightcardbg);
            c2.setBackgroundResource(R.drawable.lightcardbg);
            c3.setBackgroundResource(R.drawable.lightcardbg);
            frameLayout.setBackgroundColor(Color.parseColor("#6782b4"));
            bottomNavigationView.setItemBackgroundResource(R.drawable.lightbtmbg);
            dark=true;
        }
    }
}