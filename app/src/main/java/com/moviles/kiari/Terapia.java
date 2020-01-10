package com.moviles.kiari;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Terapia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terapia);
        getSupportActionBar().setTitle("Terapia");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
