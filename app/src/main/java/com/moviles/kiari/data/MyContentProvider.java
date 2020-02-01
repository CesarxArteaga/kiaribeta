package com.moviles.kiari.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

public class MyContentProvider extends ContentProvider {

    DataBasaHelper dbHelper;
    SQLiteDatabase db;
    private static HashMap<String, String> values;

    public static final String PROVIDER_NAME = "com.moviles.kiari.data.MyContentProvider";
    public static final String URL = "content://" + PROVIDER_NAME + "/terapia";
    public static final Uri CONTENT_URI = Uri.parse(URL);

    public static final String DATABASE_NAME = "kiari.db";
    public static final String TABLE_TERAPIA = "terapia";
    public static final String ID = "_id";
    public static final String TERAPIA_TITULO = "titulo";
    public static final String TERAPIA_DESCRIPCION = "descripcion";
    public static final String TERAPIA_SERIES = "serie";
    public static final String TERAPIA_REPETICIONES = "repeticion";

    static final UriMatcher uriMatcher;
    static final int uriCode = 1;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "terapia", uriCode);
        uriMatcher.addURI(PROVIDER_NAME, "terapia/*", uriCode);
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        dbHelper = new DataBasaHelper(context);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        db = dbHelper.getWritableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables("terapia");

        switch (uriMatcher.match(uri)) {
            case uriCode:
                qb.setProjectionMap(values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        Cursor c = qb.query(db, null, null, null, null,
                null, s1);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        db = dbHelper.getWritableDatabase();
        long rowID = db.insert(TABLE_TERAPIA, null, contentValues);

        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }

        throw new SQLiteException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
