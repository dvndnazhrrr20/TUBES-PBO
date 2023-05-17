package User;
import java.util.ArrayList;

import Kos.Kos;

public class PemilikKos extends User {
    private ArrayList<Kos> kosList;
    
    public PemilikKos(String nama, String email, String password) {
        super(nama, email, password);
        kosList = new ArrayList<>();
    }

    public boolean isKosExist(String namaKos, String lokasi){
        for(Kos kos : kosList){
            if(kos.getNamaKosan().equals(namaKos) && kos.getLokasi().equals(lokasi)){
                return true;
            }
        }
        return false;
    }

    public void addKos(Kos kos){
        kosList.add(kos);
    }

    public void updateKos(Kos kos){
        for(int i=0; i<kosList.size(); i++){
            if(kosList.get(i).getNamaKosan().equals(kos.getNamaKosan()) && kosList.get(i).getLokasi().equals(kos.getLokasi())){
                kosList.set(i, kos);
                break;
            }
        }
    }

    //Overloading
    public Kos getKos(String namaKos, String lokasi){
        for(Kos kos : kosList){
            if(kos.getNamaKosan().equals(namaKos) && kos.getLokasi().equals(lokasi)){
                return kos;
            }
        }
        return null;
    }

    //Overloading
    public ArrayList<Kos> getKos(){
        return kosList;
    }
}
