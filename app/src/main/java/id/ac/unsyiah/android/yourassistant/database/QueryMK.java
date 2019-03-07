package id.ac.unsyiah.android.yourassistant.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER PC on 01/05/2018.
 */

public class QueryMK {
    private SQLiteDatabase database;
    private DBHandler dbhelper;

    public QueryMK(Context context){
        dbhelper = new DBHandler(context);
    }

    public void open() throws SQLException{
        database = dbhelper.getWritableDatabase();
    }

    public void close(){
        dbhelper.close();
    }

    public void addData(MataKuliah mataKuliah){
        ContentValues values = new ContentValues();
        values.put(DBHandler.KODE_MK, mataKuliah.getKodeMK());
        values.put(DBHandler.NAMA_MK, mataKuliah.getNamaMK());
        values.put(DBHandler.DOSEN, mataKuliah.getDosen());
        values.put(DBHandler.RUANG_MK, mataKuliah.getRuangMK());
        values.put(DBHandler.HARI_MK, mataKuliah.getHariMK());
        values.put(DBHandler.WAKTU, mataKuliah.getWaktu());

        database.insert(DBHandler.TABLE_MATAKULIAH, null, values);
    }

    public List<MataKuliah> DataJadwalKuliah(String day){
        List<MataKuliah> data = new ArrayList<>();
        String query ="SELECT * FROM " + DBHandler.TABLE_MATAKULIAH + " WHERE " + DBHandler.HARI_MK + "=" + day + " ORDER BY "
                + DBHandler.WAKTU + " ASC";
        Cursor cursor = database.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do {
                MataKuliah mataKuliah = new MataKuliah();
                mataKuliah.setKodeMK(cursor.getString(cursor.getColumnIndex(DBHandler.KODE_MK)));
                mataKuliah.setNamaMK(cursor.getString(cursor.getColumnIndex(DBHandler.NAMA_MK)));
                mataKuliah.setDosen(cursor.getString(cursor.getColumnIndex(DBHandler.DOSEN)));
                mataKuliah.setRuangMK(cursor.getString(cursor.getColumnIndex(DBHandler.RUANG_MK)));
                mataKuliah.setHariMK(cursor.getString(cursor.getColumnIndex(DBHandler.HARI_MK)));
                mataKuliah.setWaktu(cursor.getString(cursor.getColumnIndex(DBHandler.WAKTU)));
                data.add(mataKuliah);
            }while (cursor.moveToNext());
        }
        return data;
    }

    public List<MataKuliah> DataJadwalKuliahNotice(String day, String time){
        List<MataKuliah> data = new ArrayList<>();
        String query ="SELECT * FROM " + DBHandler.TABLE_MATAKULIAH + " WHERE " + DBHandler.HARI_MK + "='" + day + "'" + " AND " + DBHandler.WAKTU
                + " LIKE '" + time + "%'";
        Cursor cursor = database.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do {
                MataKuliah mataKuliah = new MataKuliah();
                mataKuliah.setKodeMK(cursor.getString(cursor.getColumnIndex(DBHandler.KODE_MK)));
                mataKuliah.setNamaMK(cursor.getString(cursor.getColumnIndex(DBHandler.NAMA_MK)));
                mataKuliah.setDosen(cursor.getString(cursor.getColumnIndex(DBHandler.DOSEN)));
                mataKuliah.setRuangMK(cursor.getString(cursor.getColumnIndex(DBHandler.RUANG_MK)));
                mataKuliah.setHariMK(cursor.getString(cursor.getColumnIndex(DBHandler.HARI_MK)));
                mataKuliah.setWaktu(cursor.getString(cursor.getColumnIndex(DBHandler.WAKTU)));
                data.add(mataKuliah);
            }while (cursor.moveToNext());
        }
        return data;
    }

    public List<MataKuliah> DetailJadwalKuliah(String kodeMK){
        List<MataKuliah> data = new ArrayList<>();
        String query ="SELECT * FROM " + DBHandler.TABLE_MATAKULIAH + " WHERE " + DBHandler.KODE_MK + "=" + "'"+ kodeMK + "'";
        Cursor cursor = database.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do {
                MataKuliah mataKuliah = new MataKuliah();
                mataKuliah.setKodeMK(cursor.getString(cursor.getColumnIndex(DBHandler.KODE_MK)));
                mataKuliah.setNamaMK(cursor.getString(cursor.getColumnIndex(DBHandler.NAMA_MK)));
                mataKuliah.setDosen(cursor.getString(cursor.getColumnIndex(DBHandler.DOSEN)));
                mataKuliah.setRuangMK(cursor.getString(cursor.getColumnIndex(DBHandler.RUANG_MK)));
                mataKuliah.setHariMK(cursor.getString(cursor.getColumnIndex(DBHandler.HARI_MK)));
                mataKuliah.setWaktu(cursor.getString(cursor.getColumnIndex(DBHandler.WAKTU)));
                data.add(mataKuliah);
            }while (cursor.moveToNext());
        }
        return data;
    }

    public List<MataKuliah> ListJadwalBeradu(String hariMK, String jamMK){
        List<MataKuliah> data = new ArrayList<>();
        String query ="SELECT * FROM " + DBHandler.TABLE_MATAKULIAH + " WHERE " + DBHandler.HARI_MK + "=" + "'"+ hariMK + "'"
                + " AND " + DBHandler.WAKTU + "='" + jamMK + "'";
        Cursor cursor = database.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do {
                MataKuliah mataKuliah = new MataKuliah();
                mataKuliah.setKodeMK(cursor.getString(cursor.getColumnIndex(DBHandler.KODE_MK)));
                mataKuliah.setNamaMK(cursor.getString(cursor.getColumnIndex(DBHandler.NAMA_MK)));
                mataKuliah.setDosen(cursor.getString(cursor.getColumnIndex(DBHandler.DOSEN)));
                mataKuliah.setRuangMK(cursor.getString(cursor.getColumnIndex(DBHandler.RUANG_MK)));
                mataKuliah.setHariMK(cursor.getString(cursor.getColumnIndex(DBHandler.HARI_MK)));
                mataKuliah.setWaktu(cursor.getString(cursor.getColumnIndex(DBHandler.WAKTU)));
                data.add(mataKuliah);
            }while (cursor.moveToNext());
        }
        return data;
    }

    public void updateData(MataKuliah data){
        ContentValues values =new ContentValues();
        values.put(DBHandler.KODE_MK, data.getKodeMK());
        values.put(DBHandler.NAMA_MK, data.getNamaMK());
        values.put(DBHandler.DOSEN, data.getDosen());
        values.put(DBHandler.RUANG_MK, data.getRuangMK());
        values.put(DBHandler.HARI_MK, data.getHariMK());
        values.put(DBHandler.WAKTU, data.getWaktu());
        database.update(DBHandler.TABLE_MATAKULIAH,values, DBHandler.KODE_MK + "='" + data.getKodeMK() + "'", null);
    }

    public void deleteData(String kodeMK){
        database.delete(DBHandler.TABLE_MATAKULIAH, DBHandler.KODE_MK + "='" + kodeMK +"'", null);
    }
}
