package com.saadatdevelopment.lifepulse.searchdialognew.diet.DietPlanRevamp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.lifepulselibrary.DietModel;
import com.saadatdevelopment.lifepulse.searchdialognew.R;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        DietModel dietModel = intent.getParcelableExtra("diet");

        String title = "Time to Munch";
        String message = dietModel.getDescription();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationChannel channel = new NotificationChannel("munchies",title,NotificationManager.IMPORTANCE_DEFAULT);

        notificationManager.createNotificationChannel(channel);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, "munchies");

        notificationBuilder.setSmallIcon(R.drawable.add_icon);
        notificationBuilder.setContentTitle(title);
        notificationBuilder.setContentText(message);

        notificationBuilder.setAutoCancel(true);

        notificationManager.notify(1, notificationBuilder.build());

    }
}
