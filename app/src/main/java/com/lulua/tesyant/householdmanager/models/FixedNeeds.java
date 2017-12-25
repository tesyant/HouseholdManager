package com.lulua.tesyant.householdmanager.models;

public class FixedNeeds extends Needs {

    private String tanggalBayar;
    public FixedNeeds() {
        super();
    }
    public FixedNeeds(String nama, int jumlah, double harga, String tglBayar) {
        super(nama, jumlah, harga);
        this.tanggalBayar = tglBayar;
    }

    public String getTanggalBayar() {
        return tanggalBayar;
    }

    public void setTanggalBayar(String tanggalBayar) {
        this.tanggalBayar = tanggalBayar;
    }
}