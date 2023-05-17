package Sistem;

import java.util.ArrayList;

import Kos.Kos;
import User.PemilikKos;
import static Main.MamiKos.input;

public class SistemPemilik extends Sistem {
    public static ArrayList<PemilikKos> pemilik;
    private PemilikKos logged;

    public SistemPemilik(){
        super();
        pemilik = new ArrayList<>();
        logged = null;
    }

    protected void showAntreanPemilik(String emailPemilik, boolean all){
        int count = 0;
        for(Antrean antrean : waitingList){
            if(emailPemilik.equals(antrean.getEmailPemilikKos())){
                if(all){
                    count += 1;
                    System.out.println(count + ". " + antrean.getNamaKos() + " - " + antrean.getLokasiKos() + " oleh " + SistemUser.getNameUser(antrean.getEmailUser()) + " (" + antrean.getEmailUser() + ")");
                }else{
                    if(antrean.getStatus() == 0){
                        count += 1;
                        System.out.println(count + ". " + antrean.getNamaKos() + " - " + antrean.getLokasiKos() + " oleh " + SistemUser.getNameUser(antrean.getEmailUser()) + " (" + antrean.getEmailUser() + ")");
                    }
                }
            }
        }

        if(count == 0){
            System.out.println("Tidak ada waiting list pemesanan kos");
        }
    }

    protected int getCountWaitingStatusUser(String emailPemilik){
        int count = 0;
        for(Antrean antrean : waitingList){
            if(emailPemilik.equals(antrean.getEmailPemilikKos()) && antrean.getStatus() == 0){
                count += 1;
            }
        }
        return count;
    }

    protected void updateStatusWaiting(String emailPemilik, int index, int status){
        int count = 1;
        for(int i=0; i<waitingList.size(); i++){
            if(emailPemilik.equals(waitingList.get(i).getEmailPemilikKos()) && waitingList.get(i).getStatus() == 0){
                if(index == count){
                    waitingList.get(i).setStatus(status);
                    break;
                }
                count += 1;
            }
        }
    }

    private Antrean getAntrean(String email, int index, int status){
        int count = 1;
        for(int i=0; i<waitingList.size(); i++){
            if(email.equals(waitingList.get(i).getEmailPemilikKos()) && waitingList.get(i).getStatus() == status){
                if(index == count){
                    return waitingList.get(i);
                }
                count += 1;
            }
        }
        return null;
    }

    public static void showKosByLocation(String lokasi){
        int count = 0;
        for(PemilikKos pemilikKos : pemilik){
            ArrayList<Kos> kosList = pemilikKos.getKos();

            for(Kos kos : kosList){
                if(kos.getLokasi().equals(lokasi)){
                    count += 1;

                    if(count == 1){
                        System.out.println("List Kos Sekitar " + lokasi + " :");
                    }else{
                        System.out.println();
                    }

                    System.out.println(count + ". " + kos.getNamaKosan());
                    System.out.println("Fasilitas :");
                    for(String fasilitas : kos.getFasilitas()){
                        System.out.println("- " + fasilitas);
                    }
                    System.out.println("Tersedia : " + kos.getTersedia());
                    System.out.println("Harga : " + kos.getHarga());
                }
            }
        }

        if(count == 0){
            System.out.println("Tidak Ada Kos di Sekitar " + lokasi);
        }
    }

    public static void showKosByLocationTersedia(String lokasi){
        int count = 0;
        for(PemilikKos pemilikKos : pemilik){
            ArrayList<Kos> kosList = pemilikKos.getKos();

            for(Kos kos : kosList){
                if(kos.getLokasi().equals(lokasi) && kos.getTersedia() > 0){
                    count += 1;

                    if(count == 1){
                        System.out.println("List Kos Sekitar " + lokasi + " :");
                    }

                    System.out.println(count + ". " + kos.getNamaKosan() + " (" + kos.getTersedia() + " kamar tersedia)");
                }
            }
        }

        if(count == 0){
            System.out.println("Tidak Ada Kos di Sekitar " + lokasi);
        }
    }

    public static int getCountKosByLocationTersedia(String lokasi){
        int count = 0;
        for(PemilikKos pemilikKos : pemilik){
            ArrayList<Kos> kosList = pemilikKos.getKos();

            for(Kos kos : kosList){
                if(kos.getLokasi().equals(lokasi) && kos.getTersedia() > 0){
                    count += 1;
                }
            }
        }

        return count;
    }

    public static Antrean getKosByLocationIndexKos(String lokasi, int index){
        int count = 0;
        for(PemilikKos pemilikKos : pemilik){
            ArrayList<Kos> kosList = pemilikKos.getKos();

            for(Kos kos : kosList){
                if(kos.getLokasi().equals(lokasi) && kos.getTersedia() > 0){
                    count += 1;

                    if(index == count){
                        return new Antrean(null, kos.getNamaKosan(), kos.getLokasi(), pemilikKos.getEmail());
                    }
                }
            }
        }

        return null;
    }

    @Override
    public void run(){
        int menu, kosIndex, status, confirmFasilitas, indexLokasi;
        Kos kos;

        do{
            System.out.println("=== MENU PEMILIK MAMIKOS ===");
            System.out.println("1. Tambah Kos");
            System.out.println("2. Update Status Pesan Kos");
            System.out.println("3. Status Semua Pesanan Kos");
            System.out.println("4. Logout");
            System.out.print("Pilih >> ");
            menu = Integer.parseInt(input.nextLine());

            switch(menu){
                case 1:{
                    kos = new Kos();

                    do{
                        System.out.print("Masukkan Nama Kos : ");
                        kos.setNamaKosan(input.nextLine());
                    }while(kos.getNamaKosan().equals(""));

                    System.out.println("Lokasi Kos :");
                    for(int i=0; i<LOKASI.length; i++){
                        System.out.println((i+1) + ". " + LOKASI[i]);
                    }
                    do{
                        System.out.print("Pilih >> ");
                        indexLokasi = Integer.parseInt(input.nextLine());
                    }while(indexLokasi < 1 || indexLokasi > LOKASI.length);

                    kos.setLokasi(LOKASI[indexLokasi-1]);

                    if(!logged.isKosExist(kos.getNamaKosan(), kos.getLokasi())){
                        do{
                            System.out.print("Masukkan Total Kamar Kos : ");
                            kos.setKamar(Integer.parseInt(input.nextLine()));
                        }while(kos.getKamar() < 1);

                        for(String f : FASILITAS){
                            do{
                                System.out.print("Apakah ada fasilitas " + f + " [1 : Iya | 2 : Tidak] :");
                                confirmFasilitas = Integer.parseInt(input.nextLine());
                            }while(confirmFasilitas != 1 && confirmFasilitas != 2);

                            if(confirmFasilitas == 1){
                                kos.addFasilitas(f);
                            }
                        }

                        do{
                            System.out.print("Masukkan Harga Kos : ");
                            kos.setHarga(Long.parseLong(input.nextLine()));
                        }while(kos.getHarga() < 1);

                        logged.addKos(kos);
                        System.out.println("\nBerhasil menambah kos baru");
                    }else{
                        System.out.println("\nGagal menambah kos. Kos sudah terdaftar");
                    }

                    break;
                }

                case 2:{
                    if(getCountWaitingStatusUser(logged.getEmail()) > 0){
                        showAntreanPemilik(logged.getEmail(), false);
                        System.out.print("Pilih >> ");
                        kosIndex = Integer.parseInt(input.nextLine());

                        if(kosIndex >= 1 && kosIndex <= getCountWaitingStatusUser(logged.getEmail())){
                            do{
                                System.out.print("Ubah Status [1 : Terima | 2 : Tolak] : ");
                                status = Integer.parseInt(input.nextLine());
                            }while(status < 1 || status > 2);

                            updateStatusWaiting(logged.getEmail(), kosIndex, status);
                            if(status == 1){
                                Antrean tempAntrean = getAntrean(logged.getEmail(), kosIndex, status);

                                kos = logged.getKos(tempAntrean.getNamaKos(), tempAntrean.getLokasiKos());
                                kos.setTerisi(kos.getTerisi() + 1);

                                logged.updateKos(kos);
                            }
                            System.out.println("Berhasil mengubah status pemesanan kos");
                        }else{
                            System.out.println("Pilihan User Pemesan Tidak Valid!");
                        }
                    }else{
                        System.out.println("Tidak ada pencari kos yang sedang melakukan pemesanan");
                    }

                    break;
                }

                case 3:{
                    showAntreanPemilik(logged.getEmail(), true);

                    break;
                }

                case 4:{
                    System.out.println("Keluar dari pemilik mamikos...");
                    logged = null;

                    break;
                }

                default:{
                    System.out.println("Menu tidak valid");

                    break;
                }
            }

            if(menu != 4){
                System.out.println();
            }
        }while(menu != 4);
    }

    @Override
    public boolean isExist(String email) {
        for(PemilikKos p : pemilik){
            if(p.getEmail().equals(email)){
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean signup(String nama, String email, String password) {
        if(!isExist(email)){
            pemilik.add(new PemilikKos(nama, email, password));
            return true;
        }
        
        return false;
    }

    @Override
    public boolean signin(String email, String password) {
        for(PemilikKos p : pemilik){
            if(p.getEmail().equals(email) && p.getPassword().equals(password)){
                logged = p;
                return true;
            }
        }

        return false;
    }
}
