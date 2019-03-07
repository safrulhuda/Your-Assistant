package id.ac.unsyiah.android.yourassistant.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by USER PC on 30/04/2018.
 */

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "yourAssistant.db";

    public static final String TABLE_MATAKULIAH = "tabel_mahasiswa";
    public static final String TABLE_TASK = "tabel_task";

    public static final String KODE_MK = "kodeMK";
    public static final String NAMA_MK = "namaMK";
    public static final String RUANG_MK = "ruangMK";
    public static final String HARI_MK = "hariMK";
    public static final String DOSEN = "dosen";
    public static final String WAKTU = "waktu";

    public static final String NO_TASK = "noTask";
    public static final String JUDUL_TASK = "judulTask";
    public static final String DESKRIPSI_TASK = "deskripsiTask";
    public static final String TIPE_TASK = "tipeTask";
    public static final String D_DATE_TASK = "dDateTask";
    public static final String D_TIME_TASK = "dTimeTask";
    public static final String PENGINGAT_TASK = "pengingatTask";

    public DBHandler(Context context){
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_MATAKULIAH = "CREATE TABLE " + TABLE_MATAKULIAH + "("
                + KODE_MK + " TEXT PRIMARY KEY," + NAMA_MK + " TEXT,"
                + DOSEN + " TEXT," + RUANG_MK + " TEXT," + HARI_MK + " TEXT," + WAKTU + " TEXT" + ")";
        db.execSQL(CREATE_TABLE_MATAKULIAH);

        String CREATE_TABLE_TASK = "CREATE TABLE " + TABLE_TASK + "("
                + KODE_MK + " TEXT," + NO_TASK + " INTEGER PRIMARY KEY AUTOINCREMENT," + JUDUL_TASK + " TEXT,"
                + DESKRIPSI_TASK + " TEXT," + TIPE_TASK + " TEXT," + D_DATE_TASK + " TEXT," + D_TIME_TASK + " TEXT,"
                + PENGINGAT_TASK + " TEXT," + "FOREIGN KEY "+ "(" + KODE_MK +") "+ "REFERENCES "
                + TABLE_MATAKULIAH + "(" + KODE_MK + ")" + ")";
        db.execSQL(CREATE_TABLE_TASK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATAKULIAH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        onCreate(db);
    }
}