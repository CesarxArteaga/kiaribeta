package com.moviles.kiari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.moviles.kiari.data.MyContentProvider;

import java.util.ArrayList;
import java.util.List;

public class InfoTerapia extends AppCompatActivity {

    List itemsTitulo = new ArrayList();
    List itemsDescripcion = new ArrayList();
    List itemsSerie = new ArrayList();
    List itemsRepeticion = new ArrayList();
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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent mIntent = getIntent();
        id_selected = mIntent.getIntExtra("id_opc_select", 0);

        getSupportActionBar().setTitle(opciones[id_selected].toUpperCase());

        getTerapiasService();

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
        TextView descripcion = (TextView)findViewById(R.id.Descripcion);
        TextView series = (TextView)findViewById(R.id.txtSeries);
        TextView repeticionestxt = (TextView)findViewById(R.id.txtRepeticiones);

        if(itemsTitulo.size()<1){
            titulo.setText(titulos[id_selected]);
            descripcion.setText(descripciones[id_selected]);
            series.setText(seriesdiarias[id_selected]);
            repeticionestxt.setText(repeticiones[id_selected]);
        }else{
            titulo.setText(itemsTitulo.get(id_selected).toString());
            descripcion.setText(itemsDescripcion.get(id_selected).toString());
            series.setText(itemsSerie.get(id_selected).toString());
            repeticionestxt.setText(itemsRepeticion.get(id_selected).toString());
        }


    }

    public void getTerapiasService(){
        String [] projection = {"_id","titulo","descripcion","serie","repeticion"};
        String setOrder = MyContentProvider.ID + " ASC";
        Cursor cursor = getContentResolver().query(MyContentProvider.CONTENT_URI,projection,"_id",null,setOrder);

        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(MyContentProvider.ID));
            String titulo = cursor.getString(
                    cursor.getColumnIndexOrThrow(MyContentProvider.TERAPIA_TITULO));
            String descripcion = cursor.getString(
                    cursor.getColumnIndexOrThrow(MyContentProvider.TERAPIA_DESCRIPCION));
            String serie = cursor.getString(
                    cursor.getColumnIndexOrThrow(MyContentProvider.TERAPIA_SERIES));
            String repeticion = cursor.getString(
                    cursor.getColumnIndexOrThrow(MyContentProvider.TERAPIA_REPETICIONES));

            itemsTitulo.add(titulo);
            itemsDescripcion.add(descripcion);
            itemsSerie.add(serie);
            itemsRepeticion.add(repeticion);
        }
        cursor.close();
    }
}
