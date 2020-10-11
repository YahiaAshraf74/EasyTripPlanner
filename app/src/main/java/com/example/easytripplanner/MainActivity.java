package com.example.easytripplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // just for test
        Intent secondIntent = new Intent(MainActivity.this, AddTrip.class);
        startActivity(secondIntent);

    }
}