package com.example.minhasrotas.service;

import com.example.minhasrotas.entities.Ponto;
import com.example.minhasrotas.entities.Rota;

import java.util.ArrayList;
import java.util.List;

public class RotaService {

    public static Rota getRota(int id) {
        Rota rota = new Rota();
        rota.setId(id);
        rota.setNome("Rota " + id);
        rota.setDescricao("Descrição da rota " + id);
        return rota;
    }


    public static List<Rota> getRotas(int howMuch) {
        List<Rota> rotas = new ArrayList<>(howMuch);
        for (int i = 0; i < howMuch; i++) {
            rotas.add(getRota(i + 1));
        }
        return rotas;
    }

    public static Rota save(String name, String description) {
        return new Rota(1, name, description);
    }

    public static List<Ponto> getPontos(int rotaId) {
        return List.of(
            new Ponto(rotaId, 37.4219983, -122.0840000),
                new Ponto(rotaId, 37.4218983, -122.0840000),
            new Ponto(rotaId, 38.4219983, -122.0840000),
            new Ponto(rotaId, 39.4219983, -122.0840000),
            new Ponto(rotaId, 40.4219983, -122.0840000)
        );
    }

}
