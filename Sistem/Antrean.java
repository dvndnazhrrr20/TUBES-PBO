package Sistem;

public class Antrean {
    private String emailUser;
    private String namaKos;
    private String lokasiKos;
    private String emailPemilikKos;
    private int status; //0 Waiting, 1 Accepted, 2 Rejected

    public Antrean(String emailUser, String namaKos, String lokasiKos, String emailPemilikKos) {
        this.emailUser = emailUser;
        this.namaKos = namaKos;
        this.lokasiKos = lokasiKos;
        this.emailPemilikKos = emailPemilikKos;
        status = 0;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getNamaKos() {
        return namaKos;
    }

    public void setNamaKos(String namaKos) {
        this.namaKos = namaKos;
    }

    public String getLokasiKos() {
        return lokasiKos;
    }

    public void setLokasiKos(String lokasiKos) {
        this.lokasiKos = lokasiKos;
    }

    public String getEmailPemilikKos() {
        return emailPemilikKos;
    }

    public void setEmailPemilikKos(String emailPemilikKos) {
        this.emailPemilikKos = emailPemilikKos;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
