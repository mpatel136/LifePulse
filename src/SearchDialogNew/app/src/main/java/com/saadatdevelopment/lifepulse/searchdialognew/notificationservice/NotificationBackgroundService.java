package com.saadatdevelopment.lifepulse.searchdialognew.notificationservice;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import com.saadatdevelopment.lifepulse.searchdialognew.R;
import com.saadatdevelopment.lifepulse.searchdialognew.diet.HeartConditionExercises;

import java.util.Calendar;

public class NotificationBackgroundService extends JobIntentService {


    /**
     *  Displays notification even when app is closed
     *  https://stackoverflow.com/questions/46445265/android-8-0-java-lang-illegalstateexception-not-allowed-to-start-service-inten/49846410#49846410   Used to make service run in background
     * @param intent
     */
    @Override
    protected void onHandleWork(Intent intent) {

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            Intent notifyIntent = new Intent(getBaseContext(), HeartConditionExercises.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), "notify_001")
                    .setContentTitle("Lifepulse+")
                    .setContentText("Have you exercised today?")
                    .setStyle(new NotificationCompat.BigTextStyle().bigText("Have you exercised today? Click here to exercise if you haven't done so already."))
                    .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                NotificationChannel channel = new NotificationChannel("notify_001", "Lifepulse+", NotificationManager.IMPORTANCE_DEFAULT);
                channel.setDescription("Have you exercised today? Click here to exercise if you haven't done so already.");

                notificationManager.createNotificationChannel(channel);
            }
            notificationManager.notify(0, notification.build());
        }

    //Starts job intent service
    public static void enqueueWork(Context context, Intent work) {
            enqueueWork(context, NotificationBackgroundService.class, 1, work);
    }

}
