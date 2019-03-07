package id.ac.unsyiah.android.yourassistant.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import id.ac.unsyiah.android.yourassistant.MainActivity;
import id.ac.unsyiah.android.yourassistant.adapter.MatakuliahListAdapter;
import id.ac.unsyiah.android.yourassistant.database.MataKuliah;
import id.ac.unsyiah.android.yourassistant.database.QueryMK;
import id.ac.unsyiah.android.yourassistant.database.QueryTask;
import id.ac.unsyiah.android.yourassistant.database.TugasKuliah;

/**
 * Created by USER PC on 16/06/2018.
 */

public class AlarmReceiver extends BroadcastReceiver {

    //String TAG = "AlarmReceiver";

    int currentHour, currentHourTask, currentMinuteTask;
    Calendar calendar;
    Calendar calendarTask;
    List<MataKuliah> mataKuliah;
    List<TugasKuliah> tugasKuliah;
    ArrayList<String> listTask;

    @Override
    public void onReceive(Context context, Intent intent) {

        /*if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                // Set the alarm here.
                Log.d(TAG, "onReceive: BOOT_COMPLETED");
                LocalData localData = new LocalData(context);
                NotificationScheduler.setReminder(context, AlarmReceiver.class,
                        localData.get_hour(), localData.get_min());
                return;
            }
        }*/
        //Trigger the notification
        calendar = Calendar.getInstance();
        calendarTask = Calendar.getInstance();
        //String dayNotice = (String) DateFormat.format("EEEE", calendar.DAY_OF_WEEK);
        int dayNotice = calendar.get(Calendar.DAY_OF_WEEK);
        String dayOf = null;
        switch (dayNotice){
            case  Calendar.MONDAY:
                dayOf = "Monday";
                break;
            case Calendar.TUESDAY:
                dayOf = "Tuesday";
                break;
            case Calendar.WEDNESDAY:
                dayOf = "Wednesday";
                break;
            case Calendar.THURSDAY:
                dayOf = "Thursday";
                break;
            case Calendar.FRIDAY:
                dayOf = "Friday";
                break;
        }
        currentHour = calendar.get(Calendar.HOUR_OF_DAY);

        currentHourTask = calendarTask.get(Calendar.HOUR_OF_DAY);
        currentMinuteTask = calendarTask.get(Calendar.MINUTE);
        int year = calendarTask.get(Calendar.YEAR);
        int month = calendarTask.get(Calendar.MONTH);
        int dayofMonth = calendarTask.get(Calendar.DAY_OF_MONTH);

        currentHour += 1;
        String timeNotice = String.format("%02d", currentHour);

        String timeTask = String.format("%02d:%02d", currentHourTask, currentMinuteTask);
        String dateTask = String.format("%02d.%02d.%02d", year, month, dayofMonth);

        QueryMK queryMK = new QueryMK(context);

        mataKuliah = new ArrayList<>();

        queryMK.open();
        mataKuliah = queryMK.DataJadwalKuliahNotice(dayOf,timeNotice);
        queryMK.close();

        int arrayMK = mataKuliah.size();

        tugasKuliah = new ArrayList<>();
        QueryTask queryTask = new QueryTask(context);

        queryTask.open();
        tugasKuliah = queryTask.DataTaskDeadline(dateTask, timeTask);
        queryTask.close();


        int arrayTask = tugasKuliah.size();
        listTask = new ArrayList<String>();

        if(arrayMK == 1){
            String namaMKNotice = (String) mataKuliah.get(0).getNamaMK();
            String waktuMKNotice = (String) mataKuliah.get(0).getWaktu();
            String ruanganMKNotice = (String) mataKuliah.get(0).getRuangMK();
            NotificationScheduler.showNotification(context, MainActivity.class,
                    "Your Schedule", "" + namaMKNotice + " / " + waktuMKNotice + " / " + ruanganMKNotice + "/" + arrayTask);
        }

        /*if(arrayTask == 1){
            String namaTask = (String) tugasKuliah.get(0).getJudulTask();
            String tanggalTask = (String) tugasKuliah.get(0).getD_dateTask();
            String waktuTask = (String) tugasKuliah.get(0).getD_timeTask();

            NotificationScheduler.getNotificationTask(context, MainActivity.class,"Your Task","" + namaTask + "/" + tanggalTask
                    + "/" + waktuTask);
        }else if(arrayTask > 1){
            String temp = "";
            for(int i=0 ; i < arrayTask ; i++){
                listTask.add(tugasKuliah.get(i).getJudulTask().toString());
            }
            for (String s: listTask){
                temp += "  " + s ;
            }
            NotificationScheduler.getNotificationTask(context, MainActivity.class,"Your Task","" +temp);
        }*/
    }
}
