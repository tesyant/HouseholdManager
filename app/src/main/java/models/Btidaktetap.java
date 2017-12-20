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
public class Btidaktetap extends Kebutuhan{
    int tgl_beli;
    int tgl_habis;
    public Btidaktetap(String nm, int tgl_beli,int tgl_habis){
        super(nm);
        this.tgl_beli=0;
       this.tgl_habis=0;
    }
    public void setTglbeli(int tgl_beli){
        this.tgl_beli=tgl_beli;
    }
    @Override
    public int waktu(){
        return tgl_beli-tgl_beli;
    }
}
