package Kos;

import java.util.ArrayList;

public class Kos {
    private String namaKosan;
    private ArrayList<String> fasilitas;
    private int kamar;
    private int terisi;
    private long harga;
    private String lokasi;

    public Kos(){
        fasilitas = new ArrayList<>();
        terisi = 0;
    }

    public String getNamaKosan() {
        return namaKosan;
    }

    public void setNamaKosan(String namaKosan) {
        this.namaKosan = namaKosan;
    }

    public void addFasilitas(String fasilitas){
        this.fasilitas.add(fasilitas);
    }

    public ArrayList<String> getFasilitas(){
        return fasilitas;
    }

    public int getKamar() {
        return kamar;
    }

    public void setKamar(int kamar) {
        this.kamar = kamar;
    }

    public int getTerisi() {
        return terisi;
    }

    public void setTerisi(int terisi) {
        this.terisi = terisi;
    }

    public long getHarga() {
        return harga;
    }

    public void setHarga(long harga) {
        this.harga = harga;
    }

    public void setLokasi(String lokasi){
        this.lokasi = lokasi;
    }

    public String getLokasi(){
        return lokasi;
    }

    public int getTersedia(){
        return kamar - terisi;
    }
}
