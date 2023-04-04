package com.example.minhasrotas.entities;

public final class Ponto {

    private final String latitude;
    private final String longitude;

    private final Rota rota;

    public Ponto(String latitude, String longitude, Rota rota) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.rota = rota;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
