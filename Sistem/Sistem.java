 package Sistem;

import java.util.ArrayList;

public abstract class Sistem {
    protected static ArrayList<Antrean> waitingList;
    protected final String LOKASI[] = {"Aceh", "Bandung", "Jakarta", "Jogja", "Semarang"};
    protected final String FASILITAS[] = {"AC", "Kasur", "Meja", "Lemari Baju", "Kursi", "Kloset Duduk", "Shower"};

    public Sistem(){
        waitingList = new ArrayList<>();
    }

    protected boolean isExist(String emailUser, String emailPemilikKos, String namaKos, String lokasi){
        for(Antrean antrean : waitingList){
            if(emailUser.equals(antrean.getEmailUser()) && emailPemilikKos.equals(antrean.getEmailPemilikKos())
                && namaKos.equals(antrean.getNamaKos()) && lokasi.equals(antrean.getLokasiKos()) && antrean.getStatus() == 0){
                return true;
            }
        }

        return false;
    }

    public abstract void run();
    public abstract boolean isExist(String email);
    public abstract boolean signup(String nama, String email, String password);
    public abstract boolean signin(String email, String password);
}
