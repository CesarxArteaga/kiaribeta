package com.moviles.kiari;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Informacion extends AppCompatActivity {

    private ViewPager vp;
    private LinearLayout lineardots;

    private SliderAdapter slideadap;
    private TextView[] dots;
    private Button btnSiguiente;
    private int dotActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);

        ViewPager vp = (ViewPager)findViewById(R.id.vp);
        lineardots = (LinearLayout)findViewById(R.id.dotslayout);

        slideadap = new SliderAdapter(this);
        btnSiguiente = (Button)findViewById(R.id.btnSiguiente);
        btnSiguiente.setEnabled(false);
        btnSiguiente.setVisibility(View.INVISIBLE);
        vp.setAdapter(slideadap);
        addDots(0);
        vp.addOnPageChangeListener(dotsChange);
    }

    public void addDots(int position){
        dots = new TextView[3];
        lineardots.removeAllViews();
        for (int i = 0 ; i< dots.length; i++){
            dots[i]= new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(30);
            dots[i].setTextColor(getResources().getColor(R.color.gray));
            lineardots.addView(dots[i]);
        }

        if (dots.length>0){
            dots[position].setTextColor(getResources().getColor(R.color.white));
        }
    }

    ViewPager.OnPageChangeListener dotsChange = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
                addDots(position);
                dotActual = position;
                if(dotActual==2){
                    btnSiguiente.setEnabled(true);
                    btnSiguiente.setVisibility(View.VISIBLE);
            }else {
                    btnSiguiente.setEnabled(false);
                    btnSiguiente.setVisibility(View.INVISIBLE);
                }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public void continuar(View view) {
        startActivity(new Intent(this, Home.class));
    }
}
