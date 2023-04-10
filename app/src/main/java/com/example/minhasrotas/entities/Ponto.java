package com.example.minhasrotas.entities;

public final class Ponto {

    private int rotaId;
    private final double latitude;
    private final double longitude;

    public Ponto(int rotaId, double latitude, double longitude) {
        this.rotaId = rotaId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Ponto{" +
                "rotaId=" + rotaId +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    public int getRotaId() {
        return rotaId;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
