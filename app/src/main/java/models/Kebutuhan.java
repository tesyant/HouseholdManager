/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

/**
 *
 * @author Mardiyah N
 */
public abstract class Kebutuhan {
    String nm;
    int jumlah;
    int harga;
    public Kebutuhan(String name){
        nm=name;
    }
    public String getName(){
        return nm;
    }
    public void setName(String nm){
        this.nm=nm;
    }
    public void setJumlah(int jml){
        jumlah=jml;
    }
    public void setHarga(int harga){
        this.harga=harga;
    }
    abstract public int waktu();
}
