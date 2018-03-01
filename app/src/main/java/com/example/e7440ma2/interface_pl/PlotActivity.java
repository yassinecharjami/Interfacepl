package com.example.e7440ma2.interface_pl;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class PlotActivity extends AppCompatActivity{

    private GestureDetector mGestureDetector;
    private TouchEventView tev;
    private TextView message;
    private Button enreg;
    public static final String DEBUGTAG="JWP";
    double[] pctrlx;
    double[] pctrly;
    Vector<Double> myVectorx=new Vector<Double>(1,1);
    Vector<Double> myVectory=new Vector<Double>(1,1);

    Map<String, Object> arrays = new HashMap<String, Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plot);
        tev = (TouchEventView) findViewById(R.id.canva);
        message = (TextView) findViewById(R.id.textView);
        enreg = (Button) findViewById(R.id.enreg);
        // Move to another SaveActivity
        enreg.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(PlotActivity.this, SaveActivity.class);
                startActivity(i);
            }
        });

        // Create a GestureDetector and Attach listeners that'll be called for double-tap
        TouchEventView eva = new TouchEventView(this,null);
        mGestureDetector = new GestureDetector(this, eva);
        mGestureDetector.setOnDoubleTapListener(eva);
    }




    public void clearCanvas(View v){
        tev.clearCanvas();
        tev.clearTable();
        clearTbl();
    }
   // public void enregInfo(View v){tev.savefilee("traject");}


    // Getting Controle Points and Display them on screen
    public void sendCoord(View v){
        arrays = tev.ptcontrol(tev.pc);
        // pctrlx = (double[]) arrays.get("xarray");
       // pctrly = (double[]) arrays.get("yarray");
        myVectorx = (Vector<Double>) arrays.get("xarray");
        myVectory = (Vector<Double>) arrays.get("yarray");
        tev.displaycpp(myVectorx,myVectory);
        Toast.makeText(this, "Touch Screen to display control points", Toast.LENGTH_SHORT).show();
        Log.d("x", "point control: " + Arrays.toString(pctrlx));
        Log.d("y", "point control: " + Arrays.toString(pctrly));
    }

    public void clearTbl(){
        arrays.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.ctrlpt:
                Toast.makeText(this, "choosing point control degree", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.c1:
                Toast.makeText(this, "degree changed to 1", Toast.LENGTH_SHORT).show();
                tev.controlcoeff(1);
                return true;
            case R.id.c2:
                Toast.makeText(this, "degree changed to 2", Toast.LENGTH_SHORT).show();
                tev.controlcoeff(2);
                return true;
            case R.id.c3:
                Toast.makeText(this, "degree changed to 3", Toast.LENGTH_SHORT).show();
                tev.controlcoeff(3);
                return true;
            case R.id.c4:
                Toast.makeText(this, "degree changed to 4", Toast.LENGTH_SHORT).show();
                tev.controlcoeff(4);
                return true;
            case R.id.c5:
                Toast.makeText(this, "degree changed to 5", Toast.LENGTH_SHORT).show();
                tev.controlcoeff(5);
                return true;
            case R.id.intermeth:
                Toast.makeText(this, "choosing interpolation method", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.bezier:
                Toast.makeText(this, "choosed method : bezier", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.spline:
                Toast.makeText(this, "choosed method : spline", Toast.LENGTH_SHORT).show();
                return true;
            default:return super.onOptionsItemSelected(item);
        }
    }
}
