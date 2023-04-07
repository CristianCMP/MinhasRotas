package com.example.minhasrotas.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.minhasrotas.R;
import com.example.minhasrotas.entities.Ponto;
import com.example.minhasrotas.entities.Rota;

import java.sql.SQLOutput;

public class ViewPontos extends AppCompatActivity implements View.OnClickListener,LocationListener{

    private Button btnParar = null;

    private Rota rota = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pontos);


        Bundle bundle = getIntent().getExtras();

        int idRota = bundle.getInt("idRota");

        rota = new Rota();

        //remover
        rota.setNome("TESTE");
        rota.setDescricao("DDD");
        rota.setId(1);

       LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) this);

        System.out.println("star");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        //ver para pegar a localização
                        onLocationChanged(new Location("gps"));
                        Thread.sleep(30000); // dorme por 30
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        btnParar = (Button) findViewById(R.id.btnParar);

        btnParar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewPontos.this, ViewHome.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
        Ponto ponto = new Ponto(location.getLatitude(),location.getLongitude(), rota);
        // salvar ponto
    }

    @Override
    public void onClick(View view) {

    }
}