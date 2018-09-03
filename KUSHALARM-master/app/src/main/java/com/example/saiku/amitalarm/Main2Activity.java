package com.example.saiku.amitalarm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Toast.makeText(getApplicationContext(), "entering to callllllllllll", Toast.LENGTH_LONG).show();
        Intent call = new Intent(Intent.ACTION_CALL);
        call.setData(Uri.parse("tel:7604836263"));
        Toast.makeText(getApplicationContext(),"started calling...",Toast.LENGTH_LONG).show();
        if (call.resolveActivity(getPackageManager()) != null) {
            startActivity(call);
        }

    }
    }


