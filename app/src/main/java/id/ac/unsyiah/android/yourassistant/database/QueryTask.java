package id.ac.unsyiah.android.yourassistant.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER PC on 13/06/2018.
 */

public class QueryTask {
    private SQLiteDatabase database;
    private DBHandler dbhelper;

    public QueryTask(Context context){
        dbhelper = new DBHandler(context);
    }

    public void open() throws SQLException {
        database = dbhelper.getWritableDatabase();
    }

    public void close(){
        dbhelper.close();
    }

    public void addDataTask(TugasKuliah tugasKuliah){
        ContentValues values = new ContentValues();
        values.put(DBHandler.KODE_MK, tugasKuliah.getKodeMK());
        values.put(DBHandler.NO_TASK, tugasKuliah.getNoTask());
        values.put(DBHandler.JUDUL_TASK, tugasKuliah.getJudulTask());
        values.put(DBHandler.DESKRIPSI_TASK, tugasKuliah.getDeskripsiTask());
        values.put(DBHandler.TIPE_TASK, tugasKuliah.getTipeTask());
        values.put(DBHandler.D_DATE_TASK, tugasKuliah.getD_dateTask());
        values.put(DBHandler.D_TIME_TASK, tugasKuliah.getD_timeTask());
        values.put(DBHandler.PENGINGAT_TASK, tugasKuliah.getPengingatTask());

        database.insert(DBHandler.TABLE_TASK, null, values);
    }

    public List<TugasKuliah> DataTask (String kodeMK){
        List<TugasKuliah> listData = new ArrayList<>();
        String query ="SELECT * FROM " + DBHandler.TABLE_TASK + " WHERE " + DBHandler.KODE_MK + "=" + "'"+ kodeMK + "'";
        Cursor cursor = database.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do {
                TugasKuliah tugasKuliah = new TugasKuliah();
                tugasKuliah.setKodeMK(cursor.getString(cursor.getColumnIndex(DBHandler.KODE_MK)));
                tugasKuliah.setNoTask(cursor.getString(cursor.getColumnIndex(DBHandler.NO_TASK)));
                tugasKuliah.setJudulTask(cursor.getString(cursor.getColumnIndex(DBHandler.JUDUL_TASK)));
                tugasKuliah.setDeskripsiTask(cursor.getString(cursor.getColumnIndex(DBHandler.DESKRIPSI_TASK)));
                tugasKuliah.setTipeTask(cursor.getString(cursor.getColumnIndex(DBHandler.TIPE_TASK)));
                tugasKuliah.setD_dateTask(cursor.getString(cursor.getColumnIndex(DBHandler.D_DATE_TASK)));
                tugasKuliah.setD_timeTask(cursor.getString(cursor.getColumnIndex(DBHandler.D_TIME_TASK)));
                tugasKuliah.setPengingatTask(cursor.getString(cursor.getColumnIndex(DBHandler.PENGINGAT_TASK)));
                listData.add(tugasKuliah);
            }while (cursor.moveToNext());
        }
        return listData;
    }

    public List<TugasKuliah> DataTaskDeadline (String date, String time){
        List<TugasKuliah> listData = new ArrayList<>();
        String query ="SELECT * FROM " + DBHandler.TABLE_TASK + " WHERE " + DBHandler.D_DATE_TASK + "=" + "'"+ date + "'"
                + " AND " + DBHandler.D_TIME_TASK + "=" + "'"+ time +"'";
        Cursor cursor = database.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do {
                TugasKuliah tugasKuliah = new TugasKuliah();
                tugasKuliah.setKodeMK(cursor.getString(cursor.getColumnIndex(DBHandler.KODE_MK)));
                tugasKuliah.setNoTask(cursor.getString(cursor.getColumnIndex(DBHandler.NO_TASK)));
                tugasKuliah.setJudulTask(cursor.getString(cursor.getColumnIndex(DBHandler.JUDUL_TASK)));
                tugasKuliah.setDeskripsiTask(cursor.getString(cursor.getColumnIndex(DBHandler.DESKRIPSI_TASK)));
                tugasKuliah.setTipeTask(cursor.getString(cursor.getColumnIndex(DBHandler.TIPE_TASK)));
                tugasKuliah.setD_dateTask(cursor.getString(cursor.getColumnIndex(DBHandler.D_DATE_TASK)));
                tugasKuliah.setD_timeTask(cursor.getString(cursor.getColumnIndex(DBHandler.D_TIME_TASK)));
                tugasKuliah.setPengingatTask(cursor.getString(cursor.getColumnIndex(DBHandler.PENGINGAT_TASK)));
                listData.add(tugasKuliah);
            }while (cursor.moveToNext());
        }
        return listData;
    }

    public List<TugasKuliah> UpdateDataTask (String noTask){
        List<TugasKuliah> listData = new ArrayList<>();
        String query ="SELECT * FROM " + DBHandler.TABLE_TASK + " WHERE " + DBHandler.NO_TASK + "='" + noTask + "'";
        Cursor cursor = database.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do {
                TugasKuliah tugasKuliah = new TugasKuliah();
                tugasKuliah.setKodeMK(cursor.getString(cursor.getColumnIndex(DBHandler.KODE_MK)));
                tugasKuliah.setNoTask(cursor.getString(cursor.getColumnIndex(DBHandler.NO_TASK)));
                tugasKuliah.setJudulTask(cursor.getString(cursor.getColumnIndex(DBHandler.JUDUL_TASK)));
                tugasKuliah.setDeskripsiTask(cursor.getString(cursor.getColumnIndex(DBHandler.DESKRIPSI_TASK)));
                tugasKuliah.setTipeTask(cursor.getString(cursor.getColumnIndex(DBHandler.TIPE_TASK)));
                tugasKuliah.setD_dateTask(cursor.getString(cursor.getColumnIndex(DBHandler.D_DATE_TASK)));
                tugasKuliah.setD_timeTask(cursor.getString(cursor.getColumnIndex(DBHandler.D_TIME_TASK)));
                tugasKuliah.setPengingatTask(cursor.getString(cursor.getColumnIndex(DBHandler.PENGINGAT_TASK)));
                listData.add(tugasKuliah);
            }while (cursor.moveToNext());
        }
        return listData;
    }

    public void updateDataTaskProses (TugasKuliah data){
        ContentValues values = new ContentValues();
        values.put(DBHandler.KODE_MK, data.getKodeMK());
        values.put(DBHandler.NO_TASK, data.getNoTask());
        values.put(DBHandler.JUDUL_TASK, data.getJudulTask());
        values.put(DBHandler.DESKRIPSI_TASK, data.getDeskripsiTask());
        values.put(DBHandler.TIPE_TASK, data.getTipeTask());
        values.put(DBHandler.D_DATE_TASK, data.getD_dateTask());
        values.put(DBHandler.D_TIME_TASK, data.getD_timeTask());
        values.put(DBHandler.PENGINGAT_TASK, data.getPengingatTask());

        database.update(DBHandler.TABLE_TASK,values, DBHandler.NO_TASK + "=" + data.getNoTask(), null);
    }

    public void deleteDataTask (String noTask){
        database.delete(DBHandler.TABLE_TASK, DBHandler.NO_TASK + "='" + noTask + "'", null);
    }

    public void deleteDataTaskOnDeleteMK (String kodeMK){
       database.delete(DBHandler.TABLE_TASK, DBHandler.KODE_MK + "='" + kodeMK + "'", null);
    }
}
