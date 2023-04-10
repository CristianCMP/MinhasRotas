package com.example.minhasrotas.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.minhasrotas.entities.Ponto;
import com.example.minhasrotas.entities.Rota;

import java.util.ArrayList;
import java.util.List;

public class DatabaseController {

    private SQLiteDatabase db;
    private final CreateDatabase createDatabase;

    public DatabaseController(Context context){
        createDatabase = new CreateDatabase(context);
    }

    public void deleteAll() {
        db = createDatabase.getWritableDatabase();
        db.execSQL(CreateDatabase.DROP_TABLE_ROTAS);
        db.execSQL(CreateDatabase.DROP_TABLE_PONTOS);
        db.execSQL(CreateDatabase.CREATE_TABLE_ROTAS);
        db.execSQL(CreateDatabase.CREATE_TABLE_PONTOS);
        db.close();
    }

    public Rota insertRota(Rota rota) {
        db = createDatabase.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("NAME", rota.getNome());
        values.put("DESCRIPTION", rota.getDescricao());

        long id = db.insert("ROTAS", null, values);
        db.close();

        if (id == -1)
            return null;
        else
            return new Rota((int) id, rota.getNome(), rota.getDescricao());
    }

    public Ponto insertPonto(Ponto ponto) {
        db = createDatabase.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("ROTA_ID", ponto.getRotaId());
        values.put("LATITUDE", ponto.getLatitude());
        values.put("LONGITUDE", ponto.getLongitude());

        long id = db.insert("PONTOS", null, values);
        db.close();

        if (id == -1)
            return null;
        else
            return ponto;
    }

    public List<Rota> listRotas() {
        List<Rota> rotas = new ArrayList<>();
        Cursor cursor;
        db = createDatabase.getReadableDatabase();

        cursor = db.query("ROTAS", new String[]{"_ID", "NAME", "DESCRIPTION"}, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();

        while(!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("_ID"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("NAME"));
            String description = cursor.getString(cursor.getColumnIndexOrThrow("DESCRIPTION"));
            rotas.add(new Rota(id, name, description));
            cursor.moveToNext();
        }

        return rotas;
    }

    public List<Ponto> getPontos(int rotaId) {
        List<Ponto> pontos = new ArrayList<>();
        Cursor cursor;
        db = createDatabase.getReadableDatabase();
        String where = "ROTA_ID = " + rotaId;
        cursor = db.query("PONTOS", new String[]{"LATITUDE", "LONGITUDE"}, where, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();

        while(!cursor.isAfterLast()) {
            double latitude = cursor.getDouble(cursor.getColumnIndexOrThrow("LATITUDE"));
            double longitude = cursor.getDouble(cursor.getColumnIndexOrThrow("LONGITUDE"));
            pontos.add(new Ponto(rotaId, latitude, longitude));
            cursor.moveToNext();
        }

        return pontos;
    }

    public List<Ponto> getPontos() {
        List<Ponto> pontos = new ArrayList<>();
        Cursor cursor;
        db = createDatabase.getReadableDatabase();
        cursor = db.query("PONTOS", new String[]{"ROTA_ID", "LATITUDE", "LONGITUDE"}, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();

        while(!cursor.isAfterLast()) {
            int rotaId = cursor.getInt(cursor.getColumnIndexOrThrow("ROTA_ID"));
            double latitude = cursor.getDouble(cursor.getColumnIndexOrThrow("LATITUDE"));
            double longitude = cursor.getDouble(cursor.getColumnIndexOrThrow("LONGITUDE"));
            pontos.add(new Ponto(rotaId, latitude, longitude));
            cursor.moveToNext();
        }

        return pontos;
    }

}
