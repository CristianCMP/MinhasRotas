package com.example.minhasrotas.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "rotas.db";
    private static final int VERSION = 1;

    public static final String CREATE_TABLE_ROTAS = "CREATE TABLE ROTAS (_ID integer primary key autoincrement, NAME text, DESCRIPTION text)";
    public static final String CREATE_TABLE_PONTOS = "CREATE TABLE PONTOS (ROTA_ID integer, LATITUDE real, LONGITUDE real)";
    public static final String DROP_TABLE_ROTAS = "DROP TABLE IF EXISTS ROTAS";
    public static final String DROP_TABLE_PONTOS = "DROP TABLE IF EXISTS PONTOS";

    public CreateDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ROTAS);
        db.execSQL(CREATE_TABLE_PONTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_ROTAS);
        db.execSQL(DROP_TABLE_PONTOS);
        onCreate(db);
    }

}
