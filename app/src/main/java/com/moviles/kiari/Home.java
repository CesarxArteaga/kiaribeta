package com.moviles.kiari;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.moviles.kiari.data.DataBasaHelper;
import com.moviles.kiari.data.MyContentProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Home extends AppCompatActivity implements View.OnClickListener {

    RequestQueue dataQueue;
    List itemIds;

    private SensorManager mSensorManager;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dataQueue = Volley.newRequestQueue(this);

        Toolbar navbar_home = findViewById(R.id.nav_home);
        setSupportActionBar(navbar_home);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Objects.requireNonNull(mSensorManager).registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 10f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;



        Button botonTerapia  = findViewById(R.id.botonTerapias);
        Button botonHistorias  = findViewById(R.id.botonHistorias);
        Button botonTerapistas  = findViewById(R.id.botonTerapistas);


        botonTerapia.setOnClickListener(this);
        botonHistorias.setOnClickListener(this);
        botonTerapistas.setOnClickListener(this);


        itemIds = new ArrayList<>();
        comprobarTabla();

        if(itemIds.size()<1){
            if(isInternetConnection()){
                asy a = new asy();
                a.execute();
            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menu_options = getMenuInflater();
        menu_options.inflate(R.menu.menu_options,menu);
        return true;
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.botonTerapias:
               // Toast.makeText(this, "Cargando...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,Terapia.class));
                break;
            case R.id.botonHistorias:
              //  Toast.makeText(this, "Cargando...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,Historia.class));
                break;
            case R.id.botonTerapistas:
               // Toast.makeText(this, "Cargando...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,gps.class));
                break;
        }
    }


    public void creardb(){
        DataBasaHelper dbhelper = new DataBasaHelper(this);
    }

    private boolean isInternetConnection() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void comprobarTabla(){

            String [] projection = {"_id","titulo","descripcion","serie","repeticion"};
            String setOrder = MyContentProvider.ID + " ASC";
            Cursor cursor = getContentResolver().query(MyContentProvider.CONTENT_URI,projection,"_id",null,setOrder);

            while(cursor.moveToNext()) {
                long itemId = cursor.getLong(
                        cursor.getColumnIndexOrThrow(MyContentProvider.ID));
                String titulo = cursor.getString(
                        cursor.getColumnIndexOrThrow(MyContentProvider.TERAPIA_TITULO));

                itemIds.add(titulo);
            }
            cursor.close();

            Log.d("LISTSS: - ", itemIds.toString());

    }

    class asy extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {

                final String url = "https://shielded-dawn-59476.herokuapp.com/terapia";
                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject terapia = new JSONObject();
                                terapia = response.getJSONObject(i);
                                // Get the current student (json object) data
                                String titulo = terapia.getString("titulo");
                                String descripcion = terapia.getString("descripcion");
                                int serie = terapia.getInt("serie");
                                int repeticion = terapia.getInt("repeticion");

                                Log.d("Titulo------->>", titulo + descripcion + serie + repeticion);



                                ContentValues newValues = new ContentValues();
                                newValues.put(MyContentProvider.TERAPIA_TITULO, titulo);
                                newValues.put(MyContentProvider.TERAPIA_DESCRIPCION, descripcion);
                                newValues.put(MyContentProvider.TERAPIA_SERIES, serie);
                                newValues.put(MyContentProvider.TERAPIA_REPETICIONES, repeticion);

                                getContentResolver().insert(MyContentProvider.CONTENT_URI,newValues);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

                dataQueue.add(request);

            return true;
        }

    }


    private final SensorEventListener mSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta;
            if (mAccel > 24) {

                showDialoggg();


            }

        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    private void showDialoggg() {

        final Dialog dialog = new Dialog(Home.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog);

            dialog.show();


    }

    @Override
    protected void onResume() {
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }
    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }



}
