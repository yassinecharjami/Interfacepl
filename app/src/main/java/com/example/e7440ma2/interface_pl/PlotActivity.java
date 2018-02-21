package com.example.e7440ma2.interface_pl;


import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlotActivity extends AppCompatActivity{

    private GestureDetector mGestureDetector;
    private TouchEventView tev;
    private TextView message;
    private Button enreg;
    public static final String DEBUGTAG="JWP";

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

    }
