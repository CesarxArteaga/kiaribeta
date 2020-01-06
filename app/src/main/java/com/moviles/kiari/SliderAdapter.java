package com.moviles.kiari;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    public String [] slides_descripction = {
      "Recuerda que esta apliacion no remplaza la consulta médica",
      "Los videos multimedia ayudan en el tratamiento del paciente con accidente cardiovascular",
      "Solo se debe realizar los ejercicios sugeridos por el profesional a cargo"
    };

    public SliderAdapter(Context context){
        this.context = context;
    }


    @Override
    public int getCount() {
        return slides_descripction.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layoutcontent, container, false);

        TextView txtv = (TextView) view.findViewById(R.id.txtSlide);
        txtv.setText(slides_descripction[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
