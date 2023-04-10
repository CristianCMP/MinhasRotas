package com.example.minhasrotas.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.example.minhasrotas.R;
import com.example.minhasrotas.database.DatabaseController;
import com.example.minhasrotas.databinding.ActivityViewMapsBinding;
import com.example.minhasrotas.entities.Ponto;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.stream.Collectors;

public class ViewMaps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityViewMapsBinding binding;
    private int rotaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityViewMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //get rota id
        Bundle bundle = getIntent().getExtras();
        rotaId = bundle.getInt("rotaId");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();

        //carrega o banco
        DatabaseController crud = new DatabaseController(getBaseContext());

        List<LatLng> pontos = crud.getPontos(rotaId).stream()
                .map(ponto -> new LatLng(ponto.getLatitude(), ponto.getLongitude()))
                .collect(Collectors.toList());

        if (pontos.size() <= 1) {
            pontos.add(pontos.get(0));
        }

        LatLng start = pontos.get(0);
        LatLng end = pontos.get(pontos.size() - 1);

        mMap.addMarker(new MarkerOptions().position(start).title("Início"));
        mMap.addMarker(new MarkerOptions().position(end).title("Fim"));

        List<LatLng> pontosRestantes = pontos.subList(1, pontos.size() - 1);

        for (LatLng pontosRestante : pontosRestantes) {
            mMap.addCircle(
                new CircleOptions()
                    .center(pontosRestante)
                    .radius(2)
                    .fillColor(Color.argb(150, 66, 173, 244))
            );
        }

        LatLngBounds.Builder bounds = new LatLngBounds.Builder();
        for (LatLng ponto : pontos) {
            bounds.include(ponto);
        }

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds.build(), 150);
        googleMap.moveCamera(cameraUpdate);
    }

    public void onBackPressed() {
        super.onBackPressed();
        mMap.clear();
        finish();
    }

}