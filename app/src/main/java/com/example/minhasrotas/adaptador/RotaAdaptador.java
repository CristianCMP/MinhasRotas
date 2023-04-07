package com.example.minhasrotas.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.minhasrotas.R;
import com.example.minhasrotas.entities.Rota;

import java.util.List;

public class RotaAdaptador extends BaseAdapter {
    private Context context;
    private List<Rota> lista;

    public RotaAdaptador(Context context, List<Rota> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // o código para criar e retornar uma View para o item na posição 'position' vem aqui

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_main, parent, false);
        TextView textView = view.findViewById(R.id.list);
        textView.setText(lista.get(position).getId());

        return view;
    }
}
