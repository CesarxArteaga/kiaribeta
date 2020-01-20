package com.moviles.kiari;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class Preguntas_Adapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    int [] preguntas = new int [3];
    private boolean isPagingEnabled = true;

    public Preguntas_Adapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return preguntas.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view_preguntas = layoutInflater.inflate(R.layout.layout_preguntas, container, false);

        container.addView(view_preguntas);
        return view_preguntas;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }




}
