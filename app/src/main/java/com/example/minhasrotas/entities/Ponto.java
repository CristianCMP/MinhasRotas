package com.example.minhasrotas.entities;

public final class Ponto {

    private final double latitude;
    private final double longitude;

    private final Rota rota;

    public Ponto(double latitude, double longitude, Rota rota) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.rota = rota;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
