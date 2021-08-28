package com.example.weather;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

public class DisplayFragment extends Fragment {

    TextView condition,windtv,humiditytv,pressuretv, temptv,minmaxtv,coortv;
    public static ConstraintLayout c1,c2,c3,frameLayout;
    public static TextView loctv;




    public DisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_display, container, false);
        condition=v.findViewById(R.id.condition);
        windtv=v.findViewById(R.id.windtv);
        humiditytv=v.findViewById(R.id.humidityTv);
        pressuretv=v.findViewById(R.id.pressuretv);
        temptv =v.findViewById(R.id.temptv);
        minmaxtv=v.findViewById(R.id.minmaxTv);
        loctv=v.findViewById(R.id.loctv);
        coortv=v.findViewById(R.id.coortv);
        c1=v.findViewById(R.id.card1);
        c2=v.findViewById(R.id.card2);
        c3=v.findViewById(R.id.card3);
        frameLayout=v.findViewById(R.id.frameLayout);
        return v;
    }

    void setData(String cond,float wind,float humidity,float pressure,float temp,float tempmax,float tempmin,String loc,float longitude,float latitude){
        condition.setText(cond);
        windtv.setText(String.valueOf(wind)+" km/h");
        humiditytv.setText(String.valueOf(humidity+" %"));
        pressuretv.setText(String.valueOf(pressure)+" hpa");
        temptv.setText(String.valueOf(temp)+" C");
        minmaxtv.setText(String.valueOf(tempmin)+"C/"+String.valueOf(tempmax));
        loctv.setText(loc);
        coortv.setText(String.valueOf(longitude)+"/"+String.valueOf(latitude));

    }
    void  setCity(String city){
        loctv.setText(city);
    }
}