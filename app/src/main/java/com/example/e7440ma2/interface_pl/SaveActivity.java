package com.example.e7440ma2.interface_pl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SaveActivity extends AppCompatActivity {
    private TouchEventView tev;
    public String value;
    public  EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        text = (EditText) findViewById(R.id.editPlot);
         tev = new TouchEventView(this,null);
        value = text.getText().toString();
    }
   //
    public void savePlot(View v){
        String filename = text.getText().toString();
        Toast.makeText(this, filename, Toast.LENGTH_SHORT).show();
        tev.savefilee(filename);

    }
}
