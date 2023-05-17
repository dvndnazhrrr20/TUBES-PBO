package Main;
import java.util.Scanner;

import Sistem.SistemPemilik;
import Sistem.SistemUser;

public class MamiKos {
    private SistemPemilik pemilik;
    private SistemUser user;
    public static Scanner input;

    public MamiKos(){
        pemilik = new SistemPemilik();
        user = new SistemUser();
        input = new Scanner(System.in);
    }

    public void run(){
        int menu, role;
        String email, password, nama;
        
        do{
            System.out.println("=== SELAMAT DATANG DI MAMIKOS ===");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Keluar");

            System.out.print("Pilih >> ");
            menu = Integer.parseInt(input.nextLine());

            System.out.println();
            role = 0;
            if(menu == 1 || menu == 2){
                System.out.println("=== Pilih Role ===");
                System.out.println("1. Pemilik Kos");
                System.out.println("2. Pencari Kos");

                do{
                    System.out.print("Pilih >> ");
                    role = Integer.parseInt(input.nextLine());
                }while(role < 1 || role > 2);

                System.out.println();
            }

            switch(menu){
                case 1:{
                    System.out.print("Email    : ");
                    email = input.nextLine();

                    System.out.print("Password : ");
                    password = input.nextLine();

                    if(role == 1){
                        //Pemilik
                        if(pemilik.signin(email, password)){
                            System.out.println();
                            pemilik.run();
                        }else{
                            System.out.println("Email atau Password salah");
                        }
                    }else{
                        //User
                        if(user.signin(email, password)){
                            System.out.println();
                            user.run();
                        }else{
                            System.out.println("Email atau Password salah");
                        }
                    }

                    break;
                }

                case 2:{
                    System.out.print("Nama     : ");
                    nama = input.nextLine();
                    
                    System.out.print("Email    : ");
                    email = input.nextLine();

                    System.out.print("Password : ");
                    password = input.nextLine();

                    if(role == 1){
                        //Pemilik
                        if(pemilik.signup(nama, email, password)){
                            System.out.println("Berhasil mendaftar sebagai pemilik kos!");
                        }else{
                            System.out.println("Email sudah terdaftar");
                        }
                    }else{
                        //User
                        if(user.signup(nama, email, password)){
                            System.out.println("Berhasil mendaftar sebagai pencari kos!");
                        }else{
                            System.out.println("Email sudah terdaftar");
                        }
                    }

                    break;
                }

                case 3:{
                    System.out.println("Keluar dari Mamikos");

                    break;
                }

                default:{
                    System.out.println("Menu Tidak Valid!");

                    break;
                }
            }

            if(menu != 3){
                System.out.println();
            }
        }while(menu != 3);

        input.close();
    }
}
