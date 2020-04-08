package com.example.temabianca;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationManager extends ContextWrapper {
    public static final String channelID = "channelID";
    public static final String channelName = "Channel Name";

    private android.app.NotificationManager mManager;

    public NotificationManager(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, android.app.NotificationManager.IMPORTANCE_HIGH);

        getManager().createNotificationChannel(channel);
    }

    public android.app.NotificationManager getManager() {
        if (mManager == null) {
            mManager = (android.app.NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return mManager;
    }

    public NotificationCompat.Builder getChannelNotification(String todo_title) {
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle("Titlu")
                .setContentText(todo_title)
                .setSmallIcon(R.mipmap.ic_launcher);
    }
}