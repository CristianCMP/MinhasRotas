package com.example.minhasrotas.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.minhasrotas.R;
import com.example.minhasrotas.entities.Rota;

public class ViewRota extends AppCompatActivity {

    private Button btnComecar = null;
    private EditText campoNome = null;
    private EditText campoDescricao = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rota);

        btnComecar = (Button) findViewById(R.id.btnComecar);
        campoNome = (EditText) findViewById(R.id.etNomeRota);
        campoDescricao = (EditText) findViewById(R.id.etDescricaoRota);
        btnComecar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                Rota rota = new Rota(campoNome.getText().toString(),campoDescricao.getText().toString());

                //salvar ROTA
                int idRota = 1;

                Bundle bundle = new Bundle();

                bundle.putInt("idRota", idRota);

                Intent intent = new Intent(ViewRota.this, ViewPontos.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}