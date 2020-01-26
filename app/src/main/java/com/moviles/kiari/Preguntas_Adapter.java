package com.moviles.kiari;

import android.content.Context;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class Preguntas_Adapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    int [] preguntas = new int [3];
    String [] preguntas_Texto = {"¿Cómo te llamas?", "¿Cúal es tu edad?", "¿Dónde vives?"};
    private boolean isPagingEnabled = true;
    private boolean SelectedFlag;

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

        final Button bt = (Button)view_preguntas.findViewById(R.id.btnPass);
        TextView txtTitulo = (TextView) view_preguntas.findViewById(R.id.txtPregunta);
        final RadioGroup rdGroup = (RadioGroup) view_preguntas.findViewById(R.id.radioGroupPreguntas);

        txtTitulo.setText(preguntas_Texto[position]);

        for (int i = 0; i < preguntas.length; i++) {
            RadioButton rdButon = new RadioButton(view_preguntas.getContext());
            rdButon.setText("Pregunta Responder");
            rdGroup.addView(rdButon);
        }


        for (int i = 0; i < preguntas.length; i++) {
            rdGroup.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //if(rdGroup.getCheckedRadioButtonId() == -1){
                    bt.setEnabled(true);
                    //}
                }
            });
        }


        container.addView(view_preguntas);




        return view_preguntas;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }




}
