package com.lulua.tesyant.householdmanager.models;

/**
 * Created by tesyant on 21/12/17.
 */

public class UnfixedNeeds extends Needs {
    private String tglBeli;

    public UnfixedNeeds(){
        super();

    }

    public UnfixedNeeds(String nama, int jumlah, double harga, String tglBeli) {
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
