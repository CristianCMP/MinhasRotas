package com.example.minhasrotas.view;

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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

import com.example.minhasrotas.R;
import com.example.minhasrotas.adaptador.RotaAdaptador;
import com.example.minhasrotas.entities.Rota;

import java.util.ArrayList;

public class ViewHome extends AppCompatActivity implements View.OnClickListener, LocationListener {

    private Button btnNovo = null;
    private Button btnMostrar = null;

    private ListView listView = null;

    private int idRota = 0;

    private ArrayList<Rota> listaRotas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Campo com as ROTAS
        listView= (ListView) findViewById(R.id.list);

        //Aqui puxar lista do BD

        //Teste
        Rota novoItem = new Rota();
        novoItem.setId(1);
        novoItem.setDescricao("Teste");
        novoItem.setNome("Rota");

        listaRotas = new ArrayList<>();
        listaRotas.add(novoItem);

        //Não funcionar VER
        //RotaAdaptador adaptador = new RotaAdaptador(getApplicationContext(),listaRotas);
        //listView.setAdapter(adaptador);
        //adaptador.notifyDataSetChanged();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               //objetivo: capturar o ID para enviar depois
                Object itemSelecionado = parent.getItemAtPosition(position);
                Rota objetoSelecionado = (Rota) parent.getItemAtPosition(position);

                idRota = objetoSelecionado.getId();

                System.out.println("CRISTIAN ->"+objetoSelecionado.getId());
            }
        });


        btnNovo = (Button) findViewById(R.id.btnNovo);
        btnNovo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();

                idRota = 1;

                bundle.putInt("idRota", idRota);

                Intent intent = new Intent(ViewHome.this, ViewRota.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        btnMostrar = (Button) findViewById(R.id.btnMostrar);
        btnMostrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                idRota = 1;

                bundle.putInt("idRota", idRota);

                Intent intent = new Intent(ViewHome.this, ViewMaps.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
        //System.out.println("Antes" + listaLocalizacoes.size());
        //System.out.println("Latitude: "+ location.getLatitude()+" \nLongitude: " + location.getLongitude());
        //listaLocalizacoes.add(location);
       // System.out.println("Depois" + listaLocalizacoes.size());
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