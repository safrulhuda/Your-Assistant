package id.ac.unsyiah.android.yourassistant.database;

/**
 * Created by USER PC on 13/06/2018.
 */

public class TugasKuliah {
    private String kodeMK;
    private String noTask;
    private String judulTask;
    private String deskripsiTask;
    private String tipeTask;
    private String d_dateTask;
    private String d_timeTask;
    private String pengingatTask;

    public TugasKuliah(){}

    public TugasKuliah(String kodeMK, String noTask, String judulTask, String deskripsiTask, String tipeTask, String d_dateTask, String d_timeTask, String pengingatTask){
        this.kodeMK = kodeMK;
        this.noTask = noTask;
        this.judulTask = judulTask;
        this.deskripsiTask = deskripsiTask;
        this.tipeTask = tipeTask;
        this.d_dateTask = d_dateTask;
        this.d_timeTask = d_timeTask;
        this.pengingatTask = pengingatTask;
    }

    public String getKodeMK() {
        return kodeMK;
    }

    public void setKodeMK(String kodeMK) {
        this.kodeMK = kodeMK;
    }

    public String getNoTask() {
        return noTask;
    }

    public void setNoTask(String noTask) {
        this.noTask = noTask;
    }

    public String getJudulTask() {
        return judulTask;
    }

    public void setJudulTask(String judulTask) {
        this.judulTask = judulTask;
    }

    public String getDeskripsiTask() {
        return deskripsiTask;
    }

    public void setDeskripsiTask(String deskripsiTask) {
        this.deskripsiTask = deskripsiTask;
    }

    public String getTipeTask() {
        return tipeTask;
    }

    public void setTipeTask(String tipeTask) {
        this.tipeTask = tipeTask;
    }

    public String getD_dateTask() {
        return d_dateTask;
    }

    public void setD_dateTask(String d_dateTask) {
        this.d_dateTask = d_dateTask;
    }

    public String getD_timeTask() {
        return d_timeTask;
    }

    public void setD_timeTask(String d_timeTask) {
        this.d_timeTask = d_timeTask;
    }

    public String getPengingatTask() {
        return pengingatTask;
    }

    public void setPengingatTask(String pengingatTask) {
        this.pengingatTask = pengingatTask;
    }
}
