package com.lulua.tesyant.householdmanager.models;

/**
 * Created by tesyant on 21/12/17.
 */

public class UnfixedNotes extends Needs {
    private String tglBeli;

    public UnfixedNotes(){
        super();

    }

    public UnfixedNotes (String nama, int jumlah, double harga, String tglBeli) {
        super(nama, jumlah, harga);
        this.tglBeli = tglBeli;
    }

    public String getTglBeli() {
        return tglBeli;
    }

    public void setTglBeli(String tglBeli) {
        this.tglBeli = tglBeli;
    }
}
