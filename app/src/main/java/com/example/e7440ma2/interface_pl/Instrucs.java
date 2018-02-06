package com.example.e7440ma2.interface_pl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class Instrucs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrucs);
        Button bnext = (Button) findViewById(R.id.bnext);
        bnext.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(Instrucs.this, PlotActivity.class);
                startActivity(i);
            }
        });
    }
}
