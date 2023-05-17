package Sistem;
import java.util.ArrayList;

import User.User;
import static Main.MamiKos.input;

public class SistemUser extends Sistem {
    public static ArrayList<User> users;
    private User logged;

    public SistemUser(){
        super();
        users = new ArrayList<>();
        logged = null;
    }

    public static String getNameUser(String email){
        for(User user : users){
            if(user.getEmail().equals(email)){
                return user.getNama();
            }
        }
        return "";
    }

    protected void showAntreanUser(String emailUser){
        int count = 0;
        for(Antrean antrean : waitingList){
            if(emailUser.equals(antrean.getEmailUser())){
                count += 1;
                System.out.print(count + ". " + antrean.getNamaKos() + " - " + antrean.getLokasiKos());

                switch(antrean.getStatus()){
                    case 0:{
                        System.out.println(" (Waiting)");

                        break;
                    }
                    
                    case 1:{
                        System.out.println(" (Accepted)");

                        break;
                    }
                    
                    default:{
                        System.out.println(" (Rejected)");
                        
                        break;
                    }
                }
            }
        }

        if(count == 0){
            System.out.println("Tidak ada history pemesanan kos");
        }
    }

    @Override
    public void run(){
        int menu, indexLokasi, indexKos;

        do{
            System.out.println("=== MENU USER MAMIKOS ===");
            System.out.println("1. Lihat Semua Kos Berdasarkan Lokasi");
            System.out.println("2. Pesan Kos");
            System.out.println("3. Status Pesan Kos");
            System.out.println("4. Logout");
            System.out.print("Pilih >> ");
            menu = Integer.parseInt(input.nextLine());

            switch(menu){
                case 1:{
                    System.out.println("Lokasi Kos :");
                    for(int i=0; i<LOKASI.length; i++){
                        System.out.println((i+1) + ". " + LOKASI[i]);
                    }
                    System.out.print("Pilih >> ");
                    indexLokasi = Integer.parseInt(input.nextLine());
                    
                    if(indexLokasi >= 1 && indexLokasi <= LOKASI.length){
                        SistemPemilik.showKosByLocation(LOKASI[indexLokasi-1]);
                    }else{
                        System.out.println("Lokasi Kos Tidak Ditemukan!");
                    }

                    break;
                }

                case 2:{
                    System.out.println("Lokasi Kos :");
                    for(int i=0; i<LOKASI.length; i++){
                        System.out.println((i+1) + ". " + LOKASI[i]);
                    }
                    System.out.print("Pilih >> ");
                    indexLokasi = Integer.parseInt(input.nextLine());
                    
                    if(indexLokasi >= 1 && indexLokasi <= LOKASI.length){
                        if(SistemPemilik.getCountKosByLocationTersedia(LOKASI[indexLokasi-1]) > 0){
                            SistemPemilik.showKosByLocationTersedia(LOKASI[indexLokasi-1]);
                            System.out.print("Pilih >> ");
                            indexKos = Integer.parseInt(input.nextLine());

                            if(indexKos >= 1 && indexKos <= SistemPemilik.getCountKosByLocationTersedia(LOKASI[indexLokasi-1])){
                                Antrean antrean = SistemPemilik.getKosByLocationIndexKos(LOKASI[indexLokasi-1], indexKos);
                                antrean.setEmailUser(logged.getEmail());

                                if(!isExist(logged.getEmail(), antrean.getEmailPemilikKos(), antrean.getNamaKos(), antrean.getLokasiKos())){
                                    Sistem.waitingList.add(antrean);
                                    System.out.println("Berhasil Melakukan Pemesanan Kos. Mohon Menunggu Konfirmasi Pemilik Kosan");
                                }else{
                                    System.out.println("Gagal Melakukan Pemesanan Kos. Pemesanan Sebelumnya Sedang diproses");
                                }
                            }else{
                                System.out.println("Gagal Melakukan Pemesanan Kos. Kos Tidak Ditemukan!");
                            }
                        }else{
                            System.out.println("Lokasi Kos di Sekitar " + LOKASI[indexLokasi-1] + " Tidak Ada yang Tersedia");
                        }
                    }else{
                        System.out.println("Lokasi Kos Tidak Ditemukan!");
                    }

                    break;
                }

                case 3:{
                    showAntreanUser(logged.getEmail());

                    break;
                }

                case 4:{
                    System.out.println("Keluar dari user mamikos...");
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
        for(User user : users){
            if(user.getEmail().equals(email)){
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean signup(String nama, String email, String password) {
        if(!isExist(email)){
            users.add(new User(nama, email, password));
            return true;
        }
        
        return false;
    }

    @Override
    public boolean signin(String email, String password) {
        for(User user : users){
            if(user.getEmail().equals(email) && user.getPassword().equals(password)){
                logged = user;
                return true;
            }
        }

        return false;
    }
}
