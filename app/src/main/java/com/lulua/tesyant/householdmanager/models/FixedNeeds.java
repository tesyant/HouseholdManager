package com.lulua.tesyant.householdmanager.models;

import java.util.Date;

public class FixedNeeds extends Needs {

    private Date tanggalBayar;

    public FixedNeeds(String nama, int jumlah, double harga, Date tglBayar) {
        super(nama, jumlah, harga);
        this.tanggalBayar = tglBayar;
    }

    public Date getTanggalBayar() {
        return tanggalBayar;
    }

    public void setTanggalBayar(Date tanggalBayar) {
        this.tanggalBayar = tanggalBayar;
    }
}