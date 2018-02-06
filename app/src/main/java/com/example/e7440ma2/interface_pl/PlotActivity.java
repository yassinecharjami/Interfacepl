package com.example.e7440ma2.interface_pl;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlotActivity extends AppCompatActivity {

    private TouchEventView tev;
    private TextView message;
    private GestureDetectorCompat gestureDetector;
    public static final String DEBUGTAG="JWP";
    Button enreg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plot);
        tev = (TouchEventView) findViewById(R.id.canva);
      //  enreg = (Button) findViewById(R.id.enreg);
     /*   enreg.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
              tev.savetofile();
            }
        }); */

    }

    public void clearCanvas(View v){
        tev.clearCanvas();
    }
    public void enregInfo(View v){
        tev.savetofile();
    }

}
