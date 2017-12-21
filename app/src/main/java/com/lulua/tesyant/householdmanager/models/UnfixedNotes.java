package com.lulua.tesyant.householdmanager.models;

import java.util.Date;

/**
 * Created by tesyant on 21/12/17.
 */

public class UnfixedNotes extends Needs {
    private Date tglBeli;

    public UnfixedNotes (String nama, int jumlah, double harga, Date tglBeli) {
        super(nama, jumlah, harga);
        this.tglBeli = tglBeli;
    }

    public Date getTglBeli() {
        return tglBeli;
    }

    public void setTglBeli(Date tglBeli) {
        this.tglBeli = tglBeli;
    }
}
