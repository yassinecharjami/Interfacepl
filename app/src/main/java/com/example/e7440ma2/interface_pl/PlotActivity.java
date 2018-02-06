package com.example.e7440ma2.interface_pl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PlotActivity extends AppCompatActivity {

    private TouchEventView tev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plot);

        tev = (TouchEventView) findViewById(R.id.canva);
    }

    public void clearCanvas(View v){
        tev.clearCanvas();
    }
}
