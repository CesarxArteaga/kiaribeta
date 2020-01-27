package com.moviles.kiari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class InfoTerapia extends AppCompatActivity {

    int id_selected;
    String [] opciones = {"brazo","pierna","cuello","sentarse","mano","caminar"};
    String [] titulos = {"Ejercicio Brazos","Ejercicio Pierna","Ejercicio Cuello","Ejercicio Sentarse","Ejercicio Mano","Ejercicio Caminar"};
    String [] descripciones = {"Para mantener el brazo fuerte y los músculos activos:",
    "Para mantener la pierna fuerte y los músculos activos:",
    "Para mantener el cuello sano:",
    "Aprende a sentarte correctamente y mantener bien la columna:",
    "Ejercitar la mano para tenerla sana:",
    "Para recuperar la marcha y activar los músculos inferiores:"};
    String [] repeticiones = {"10","15","20","15","20","15"};
    String [] seriesdiarias = {"4","5","3","4","4","5"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_terapia);
        getSupportActionBar().setTitle(opciones[id_selected]);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent mIntent = getIntent();
        id_selected = mIntent.getIntExtra("id_opc_select", 0);

        VideoView video = (VideoView)findViewById(R.id.videoView);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.ejercicio_brazo;
        Uri uri = Uri.parse(path);
        video.setVideoURI(uri);

        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        video.start();

        TextView titulo = (TextView)findViewById(R.id.Titulo);
        titulo.setText(titulos[id_selected]);

        TextView descripcion = (TextView)findViewById(R.id.Descripcion);
        descripcion.setText(descripciones[id_selected]);

        TextView series = (TextView)findViewById(R.id.txtSeries);
        series.setText(seriesdiarias[id_selected]);

        TextView repeticionestxt = (TextView)findViewById(R.id.txtRepeticiones);
        repeticionestxt.setText(repeticiones[id_selected]);

    }
}
