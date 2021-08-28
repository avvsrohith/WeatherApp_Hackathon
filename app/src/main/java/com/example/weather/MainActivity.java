package com.example.weather;

import static com.example.weather.API.API_utilities.API_KEY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weather.API.API_utilities;
import com.example.weather.Model.Weather;
import com.example.weather.Model.WeatherInfo;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        failtv=findViewById(R.id.Failtv);
        locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},PERMISSION_CODE);
        }

        Location location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        cityname=getCity(location.getLongitude(),location.getLatitude());



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
                    else{
                        Toast.makeText(this, "City not found", Toast.LENGTH_SHORT).show();
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
                            fragmentContainerView.setVisibility(View.INVISIBLE);
                            failtv.setVisibility(View.VISIBLE);
                        } else{
                            failtv.setVisibility(View.INVISIBLE);
                            WeatherInfo info = response.body();
                        displayFragment.setData(info.getName());
                    }
                    }

                    @Override
                    public void onFailure(Call<WeatherInfo> call, Throwable t) {

                    }
                });
    }
}