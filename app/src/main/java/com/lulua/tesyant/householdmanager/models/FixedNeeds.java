package com.lulua.tesyant.householdmanager.models;

import android.os.Parcel;
import android.os.Parcelable;

public class FixedNeeds extends Needs implements Parcelable {

    private String tanggalBayar;

    public FixedNeeds() {
        super();
    }

    public String getTanggalBayar() {
        return tanggalBayar;
    }

    public void setTanggalBayar(String tanggalBayar) {
        this.tanggalBayar = tanggalBayar;
    }

    public FixedNeeds(String nama, int jumlah, double harga, String tglBayar) {
        super(nama, jumlah, harga);
        this.tanggalBayar = tglBayar;
    }

    protected FixedNeeds(Parcel in) {
        tanggalBayar = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(tanggalBayar);
    }

    public static final Creator<FixedNeeds> CREATOR = new Creator<FixedNeeds>() {
        @Override
        public FixedNeeds createFromParcel(Parcel in) {
            return new FixedNeeds(in);
        }

        @Override
        public FixedNeeds[] newArray(int size) {
            return new FixedNeeds[size];
        }
    };
}