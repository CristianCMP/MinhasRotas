package com.example.minhasrotas.view;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.minhasrotas.R;
import com.example.minhasrotas.database.DatabaseController;
import com.example.minhasrotas.entities.Rota;
import com.example.minhasrotas.service.RotaService;

public class ViewHome extends AppCompatActivity implements View.OnClickListener, LocationListener {

    private ListView listView;
    private Button btnNovo;
    private Button btnDeleteAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instância objetos
        listView = findViewById(R.id.list);
        btnNovo = findViewById(R.id.btnNovo);
        btnDeleteAll = findViewById(R.id.buttonDeleteAll);

        //carrega o banco
        DatabaseController crud = new DatabaseController(getBaseContext());

        //carrega lista
        ListAdapter listAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1,
                crud.listRotas()
        );
        listView.setAdapter(listAdapter);

        //event listener click
        listView.setOnItemClickListener((parent, view, position, id) -> {
           //objetivo: capturar o ID para enviar depois
            Object itemSelecionado = parent.getItemAtPosition(position);
            Rota objetoSelecionado = (Rota) parent.getItemAtPosition(position);

            //direciona para o maps
            Intent intent = new Intent(ViewHome.this, ViewMaps.class);
            Bundle bundle = new Bundle();
            bundle.putInt("rotaId", objetoSelecionado.getId());
            intent.putExtras(bundle);
            startActivity(intent);
        });

        //event click novo
        btnNovo.setOnClickListener(view -> {
            Intent intent = new Intent(ViewHome.this, ViewRota.class);
            startActivity(intent);
        });

        //event click delete All
        btnDeleteAll.setOnClickListener(view -> {
            crud.deleteAll();
            this.recreate();
        });

    }

    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onClick(View view) {
    }

}