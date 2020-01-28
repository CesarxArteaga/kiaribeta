package com.moviles.kiari;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Home extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Toolbar navbar_home = findViewById(R.id.nav_home);
        setSupportActionBar(navbar_home);
        //navbar_home.setLogo(R.drawable.logo_navbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        Button botonTerapia  = findViewById(R.id.botonTerapias);
        Button botonHistorias  = findViewById(R.id.botonHistorias);
        Button botonTerapistas  = findViewById(R.id.botonTerapistas);


        botonTerapia.setOnClickListener(this);
        botonHistorias.setOnClickListener(this);
        botonTerapistas.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menu_options = getMenuInflater();
        menu_options.inflate(R.menu.menu_options,menu);
        return true;
    }

    public void goTerapia(View view) {
//        startActivity(new Intent(this,Terapia.class));
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.botonTerapias:
                Toast.makeText(this, "Cargando...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,Terapia.class));
                break;
            case R.id.botonHistorias:
                Toast.makeText(this, "Cargando...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,Historia.class));
                break;
            case R.id.botonTerapistas:
                Toast.makeText(this, "Cargando...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,gps.class));

                break;
        }
    }


//    public void goHistoria(View view) {
//        startActivity(new Intent(this,Historias.class));
//    }


}
