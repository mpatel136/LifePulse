package com.saadatdevelopment.lifepulse.searchdialognew.notificationservice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.saadatdevelopment.lifepulse.searchdialognew.MainActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//https://stackoverflow.com/questions/34087648/scheduled-notifications-triggers-every-time-i-open-app   Used to send notification only when time comes
public class MyReceiver extends BroadcastReceiver {

    public MyReceiver() {

    }

    //Sets time for alarm to go off once a day at 5pm
    public static void setUpAlarm(Context context) {

        long t = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 17);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        if(t <= calendar.getTimeInMillis()) {
            Intent notifyIntent = new Intent(context, MyReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(MainActivity.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }
    }

    //Call job service to start work
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationBackgroundService.enqueueWork(context,intent);
    }
}
