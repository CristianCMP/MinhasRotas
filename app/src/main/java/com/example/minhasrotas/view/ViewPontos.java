package com.example.minhasrotas.view;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.minhasrotas.R;
import com.example.minhasrotas.database.DatabaseController;
import com.example.minhasrotas.entities.Ponto;

public class ViewPontos extends AppCompatActivity implements View.OnClickListener, LocationListener {

    private Button btnParar;
    private TextView feedback;
    private int rotaId;
    private int registers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pontos);

        //instância objetos
        btnParar = findViewById(R.id.btnParar);
        feedback = findViewById(R.id.textView5);

        //get rota id
        Bundle bundle = getIntent().getExtras();
        rotaId = bundle.getInt("rotaId");

        //verifica se contem a permissão e define as configurações para pegar as localizações
        requestLocation();

        btnParar.setOnClickListener(view -> {
            LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            mlocManager.removeUpdates(this);
            Intent intent = new Intent(ViewPontos.this, ViewHome.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public void onLocationChanged(Location location) {
        Ponto ponto = new Ponto(rotaId, location.getLatitude(), location.getLongitude());
        registers++;

        //carrega o banco
        DatabaseController crud = new DatabaseController(getBaseContext());
        crud.insertPonto(ponto);

        feedback.setText("Registros realizados: " + registers + "\n" + ponto);
    }

    @Override
    public void onClick(View view) {

    }

    private void requestLocation() {
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        int fineLocationAccess = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int coarseLocationAccess = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);

        if (fineLocationAccess != PERMISSION_GRANTED && coarseLocationAccess != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }

        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 2, this::onLocationChanged);
    }

}