package com.example.e7440ma2.interface_pl;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.io.IOException;

public class Traject extends AppCompatActivity {

    private TouchEventView canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(new TouchEventView(this, null));
    }


    public void clearCanvas(View v) {
        canvas.clearCanvas();
    }

}
