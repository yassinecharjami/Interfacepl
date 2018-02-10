package com.example.e7440ma2.interface_pl;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PlotActivity extends AppCompatActivity{

    private GestureDetector mGestureDetector;
    private TouchEventView tev;
    private View v;
    private TextView message;
    //private GestureDetectorCompat mGestureDetector;
    public static final String DEBUGTAG="JWP";
    Button enreg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plot);
        tev = (TouchEventView) findViewById(R.id.canva);
        message = (TextView) findViewById(R.id.textView);
        TouchEventView eva = new TouchEventView(this,null);
        // Create a GestureDetector
        mGestureDetector = new GestureDetector(this, eva);
        // Attach listeners that'll be called for double-tap and related gestures
        mGestureDetector.setOnDoubleTapListener(eva);
    }

        //  enreg = (Button) findViewById(R.id.enreg);
     /*   enreg.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
              tev.savetofile();
            }
        }); */



    public void clearCanvas(View v){
        tev.clearCanvas();
    }
    public void enregInfo(View v){tev.savefilee("traject");}

}
