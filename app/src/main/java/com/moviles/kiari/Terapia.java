package com.moviles.kiari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.moviles.kiari.data.DataBasaHelper;

public class Terapia extends AppCompatActivity {

    LinearLayout [] layouts_select = new LinearLayout[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_terapia);
        getSupportActionBar().setTitle("TERAPIAS");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        layouts_select[0] = (LinearLayout)findViewById(R.id.layout_brazo);
        layouts_select[1] = (LinearLayout)findViewById(R.id.layout_pierna);
        layouts_select[2] = (LinearLayout)findViewById(R.id.layout_cuello);
        layouts_select[3] = (LinearLayout)findViewById(R.id.layout_sentarse);
        layouts_select[4] = (LinearLayout)findViewById(R.id.layout_mano);
        layouts_select[5] = (LinearLayout)findViewById(R.id.layout_caminar);



        for (int i = 0; i < 6; i++) {
            final int id = i;
            layouts_select[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    click(id);
                }
            });
        }



        /*if(isNetworkAvailable()){
            Toast t = Toast.makeText(this,"Conectado",Toast.LENGTH_LONG);
            t.show();
        }else {
            Toast t = Toast.makeText(this,"Sin internet",Toast.LENGTH_LONG);
            t.show();
        }*/

    }

    public void click(int id){
        //Toast t = Toast.makeText(this,"Works",Toast.LENGTH_SHORT);
        //t.show();
        Intent mIntent = new Intent(this,InfoTerapia.class);
        mIntent.putExtra("id_opc_select",id);
        startActivity(mIntent);

    }





}
