package com.moviles.kiari.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBasaHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "kiari.db";
    private static final String TABLE_TERAPIA = "terapia";
    private static final String ID = "_id";
    private static final String TERAPIA_TITULO = "titulo";
    private static final String TERAPIA_DESCRIPCION = "descripcion";
    private static final String TERAPIA_SERIES = "serie";
    private static final String TERAPIA_REPETICIONES = "repeticion";
    String statementTerapia = " CREATE TABLE IF NOT EXISTS " + TABLE_TERAPIA + "( "+ ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,"
            + TERAPIA_TITULO + " VARCHAR(200) NOT NULL , " + TERAPIA_DESCRIPCION + " VARCHAR(200) NOT NULL , " + TERAPIA_SERIES +" INTEGER NOT NULL , "
            + TERAPIA_REPETICIONES + " INTEGER NOT NULL );";

    public DataBasaHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(statementTerapia);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
