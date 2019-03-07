package id.ac.unsyiah.android.yourassistant.database;

/**
 * Created by USER PC on 30/04/2018.
 */

public class MataKuliah {
    private String kodeMK;
    private String namaMK;
    private String ruangMK;
    private String hariMK;
    private String dosen;
    private String waktu;

    public MataKuliah(){}

    public MataKuliah(String kodeMK, String namaMK, String dosen, String ruangMK, String hariMK, String waktu){
        this.kodeMK = kodeMK;
        this.namaMK = namaMK;
        this.ruangMK = ruangMK;
        this.hariMK = hariMK;
        this.dosen = dosen;
        this.waktu = waktu;
    }

    public String getKodeMK() {
        return kodeMK;
    }

    public void setKodeMK(String kodeMK) {
        this.kodeMK = kodeMK;
    }

    public String getNamaMK() {
        return namaMK;
    }

    public void setNamaMK(String namaMK) {
        this.namaMK = namaMK;
    }

    public String getRuangMK() {
        return ruangMK;
    }

    public void setRuangMK(String ruangMK) {
        this.ruangMK = ruangMK;
    }

    public String getHariMK() {
        return hariMK;
    }

    public void setHariMK(String hariMK) {
        this.hariMK = hariMK;
    }

    public String getDosen() {
        return dosen;
    }

    public void setDosen(String dosen) {
        this.dosen = dosen;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }
}
