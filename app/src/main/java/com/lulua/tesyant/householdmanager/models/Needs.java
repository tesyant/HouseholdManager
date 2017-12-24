package com.lulua.tesyant.householdmanager.models;

public class Needs {

    private String nama;
    private int jumlah;
    private double harga;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Needs(String nama, int jumlah, double harga) {
        this.nama = nama;
        this.jumlah = jumlah;
        this.harga = harga;
    }

    public Needs() {
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

}
