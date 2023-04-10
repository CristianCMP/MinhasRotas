package com.example.minhasrotas.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.minhasrotas.R;
import com.example.minhasrotas.database.DatabaseController;
import com.example.minhasrotas.entities.Rota;
import com.example.minhasrotas.service.RotaService;

public class ViewRota extends AppCompatActivity {

    private Button btnComecar;
    private EditText campoNome;
    private EditText campoDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rota);

        //carrega o banco
        DatabaseController crud = new DatabaseController(getBaseContext());

        //instância objetos
        btnComecar = findViewById(R.id.btnComecar);
        campoNome = findViewById(R.id.etNomeRota);
        campoDescricao = findViewById(R.id.etDescricaoRota);

        //event click começar
        btnComecar.setOnClickListener(view -> {
            String name = campoNome.getText().toString();
            String description = campoDescricao.getText().toString();
            Rota rota = crud.insertRota(new Rota(name, description));
            Intent intent = new Intent(ViewRota.this, ViewPontos.class);
            Bundle bundle = new Bundle();
            bundle.putInt("rotaId", rota.getId());
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        });
    }

}