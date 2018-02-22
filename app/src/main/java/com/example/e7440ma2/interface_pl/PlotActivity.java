package com.example.e7440ma2.interface_pl;


import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PlotActivity extends AppCompatActivity{

    private GestureDetector mGestureDetector;
    private TouchEventView tev;
    private TextView message;
    private Button enreg;
    public static final String DEBUGTAG="JWP";
    double ptcontr[] = new double[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plot);
        tev = (TouchEventView) findViewById(R.id.canva);
        message = (TextView) findViewById(R.id.textView);
        enreg = (Button) findViewById(R.id.enreg);
        enreg.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Intent i = new Intent(PlotActivity.this, SaveActivity.class);
                startActivity(i);
            }
        });
        TouchEventView eva = new TouchEventView(this,null);
        // Create a GestureDetector
        mGestureDetector = new GestureDetector(this, eva);
        // Attach listeners that'll be called for double-tap and related gestures
        mGestureDetector.setOnDoubleTapListener(eva);
    }




    public void clearCanvas(View v){
        tev.clearCanvas();
    }
    public void enregInfo(View v){tev.savefilee("traject");}

    double pctrlx[] = new double[100];
    double pctrly[] = new double[100];
    Map<String, Object> arrays = new HashMap<String, Object>();


    public void sendCoord(View v){
      //  pctrlx = tev.ptcontrolx(tev.pc);
      //  pctrly = tev.ptcontroly(tev.pc);
        arrays = tev.ptcontrol(tev.pc);
         pctrlx = (double[]) arrays.get("xarray");
         pctrly = (double[]) arrays.get("yarray");
        Log.d("x", "point control: " + Arrays.toString(pctrlx));
        Log.d("y", "point control: " + Arrays.toString(pctrly));
        Log.d("plot", "xfloatplotact: " + Arrays.toString(tev.xfloat));
        Log.d("plot", "yfloatplotact" + Arrays.toString(tev.yfloat));
    }

    }
