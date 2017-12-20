package models;

public class Btetap extends Kebutuhan{
    int tgl_bayar;
    int jangka;
    public Btetap(String nm){
       super(nm); 
    }
    public int getTglbayar(int tgl_bayar){
        return tgl_bayar;
    }
    public void setTglbayar(int tgl_bayar){
        this.tgl_bayar=tgl_bayar;
    }
    @Override
    public int waktu(){
        return jangka;
    }



    
    
}
