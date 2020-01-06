package com.moviles.kiari;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.LinearLayout;

public class Informacion extends AppCompatActivity {

    private ViewPager vp;
    private LinearLayout lineardots;

    private SliderAdapter slideadap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);

        ViewPager vp = (ViewPager)findViewById(R.id.vp);
        LinearLayout lineardots = (LinearLayout)findViewById(R.id.dotslayout);

        slideadap = new SliderAdapter(this);

        vp.setAdapter(slideadap);
    }

}
