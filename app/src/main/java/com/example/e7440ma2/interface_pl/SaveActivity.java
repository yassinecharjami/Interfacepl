package com.example.e7440ma2.interface_pl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SaveActivity extends AppCompatActivity {
    private TouchEventView tev;
    private String filename;
    private EditText edt;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

         edt = (EditText) findViewById(R.id.editText);
         filename = edt.getText().toString();
         tev = (TouchEventView) findViewById(R.id.canva);

        save = (Button) findViewById(R.id.saveButton);
        save.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
             //   tev.savefilee(filename);
            }
        });
    }
}
