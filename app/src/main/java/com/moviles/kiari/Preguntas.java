package com.moviles.kiari;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Preguntas extends AppCompatActivity {

    public String [] opciones = {"brazo","pierna","cuello","sentarse","mano","caminar"};
    int id_selected;

    private ViewPager vp_preguntas;
    private Preguntas_Adapter slideadap_preguntas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);
        getSupportActionBar().setTitle("Preguntas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent mIntent = getIntent();
        id_selected = mIntent.getIntExtra("id_opc_select", 0);

        getSupportActionBar().setTitle(opciones[id_selected].toUpperCase());


        vp_preguntas = (ViewPager)findViewById(R.id.vp_preguntas);
        slideadap_preguntas = new Preguntas_Adapter(this);
        vp_preguntas.setAdapter(slideadap_preguntas);
        vp_preguntas.beginFakeDrag();


    }

    public void Pass(View view) {
            if(vp_preguntas.getCurrentItem()<2){
                vp_preguntas.setCurrentItem(vp_preguntas.getCurrentItem()+1);
            }
    }




}
