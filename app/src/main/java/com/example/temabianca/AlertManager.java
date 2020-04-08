package com.example.temabianca;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class AlertManager extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
            NotificationManager notificationManager = new NotificationManager(context);
            NotificationCompat.Builder nb = notificationManager.getChannelNotification(intent.getStringExtra(Constants.INTENT_DATA));
            notificationManager.getManager().notify(1,nb.build());
    }
}
