package id.ac.unsyiah.android.yourassistant.alarm;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.text.format.DateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import id.ac.unsyiah.android.yourassistant.R;
import id.ac.unsyiah.android.yourassistant.database.MataKuliah;
import id.ac.unsyiah.android.yourassistant.database.QueryMK;

/**
 * Created by USER PC on 16/06/2018.
 */

public class NotificationScheduler {

    public static void setReminder(Context context, Class<?> cls, int hour, int min, int getDay) {
        //Calendar calendar = Calendar.getInstance();
        Calendar setcalendar = Calendar.getInstance();
        setcalendar.set(Calendar.HOUR_OF_DAY, hour);
        setcalendar.set(Calendar.MINUTE, min);
        setcalendar.set(Calendar.SECOND, 0);
        setcalendar.set(Calendar.DAY_OF_WEEK, getDay);

        //if(setcalendar.before(calendar))
          //  setcalendar.add(Calendar.DATE,1);

        // Enable a receiver
        ComponentName receiver = new ComponentName(context, cls);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);

        Intent intent1 = new Intent(context, cls);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                1, intent1,
                PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.setInexactRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    public static void showNotification(Context context,Class<?> cls,String title,String content) {

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent notificationIntent = new Intent(context, cls);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(cls);
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(
                1,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        Notification notification = builder.setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true)
                .setSound(alarmSound)
                .setColor(context.getResources().getColor(R.color.colorBiruPekat))
                .setSubText("Berikut jadwal kuliah hari ini !")
                .setSmallIcon(R.drawable.baseline_access_alarm_white_18dp)
                .setContentIntent(pendingIntent).build();

        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

    public static void showNotificationWarning(Context context,Class<?> cls, String title,String content) {

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent notificationIntent = new Intent(context, cls);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(cls);
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(
                1,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        Notification notification = builder.setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true)
                .setSound(alarmSound)
                .setColor(context.getResources().getColor(R.color.colorMerahPudar))
                .setSubText("PERHATIAN: ada jadwal yang Match !")
                .setSmallIcon(R.drawable.baseline_access_alarm_white_18dp)
                .build();

        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

    public static void cancelReminder(Context context,Class<?> cls, int hours, int mins, int getDay) {
        //Calendar calendar = Calendar.getInstance();
        Calendar setcalendar = Calendar.getInstance();
        setcalendar.set(Calendar.HOUR_OF_DAY, hours);
        setcalendar.set(Calendar.MINUTE, mins);
        setcalendar.set(Calendar.SECOND, 0);
        setcalendar.set(Calendar.DAY_OF_WEEK, getDay);

        /*if(setcalendar.before(calendar))
            setcalendar.add(Calendar.DATE,1);*/

        // Disable a receiver
        ComponentName receiver = new ComponentName(context, cls);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);

        Intent intent1 = new Intent(context, cls);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                1, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.setInexactRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);
        am.cancel(pendingIntent);
        pendingIntent.cancel();
    }

    public static void scheduleNotification(Context context, Class<?> cls, int hoursTask, int minutesTask, int day, int month, int year, String reminder){

        Calendar setcalendar = Calendar.getInstance();
        setcalendar.set(Calendar.HOUR_OF_DAY, hoursTask);
        setcalendar.set(Calendar.MINUTE, minutesTask);
        setcalendar.set(Calendar.SECOND, 0);
        setcalendar.set(Calendar.DAY_OF_MONTH, day);
        setcalendar.set(Calendar.MONTH, month);
        setcalendar.set(Calendar.YEAR, year);

        /*if(reminder.equals("1Hari")){
            setcalendar.add(Calendar.DATE, -1);
        }else if(reminder.equals("2Hari")){
            setcalendar.add(Calendar.DATE, -2);
        }else if(reminder.equals("3Hari")){
            setcalendar.add(Calendar.DATE, -3);
        }*/

        ComponentName receiver = new ComponentName(context, cls);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);

        Intent intent1 = new Intent(context, cls);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                1, intent1,
                PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), pendingIntent);
    }

    public static void getNotificationTask(Context context,Class<?> cls, String title,String content){
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent notificationIntent = new Intent(context, cls);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(cls);
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(
                1,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        Notification notification = builder.setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true)
                .setSound(alarmSound)
                .setColor(context.getResources().getColor(R.color.colorOrangeCerah))
                .setSubText("DEADLINE TASK !")
                .setSmallIcon(R.drawable.baseline_access_alarm_white_18dp)
                .build();

        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);

    }
}
