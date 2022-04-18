package com.example.petshop;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {
    boolean connected=true;
    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default" ;
    @Override
    public void onReceive(Context context, Intent intent) {
        String noti=intent.getStringExtra("noti");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,default_notification_channel_id)
                .setSmallIcon(R.drawable. ic_launcher_foreground )
                .setContentTitle("PET'S CORNER")
                .setContentText(noti)
                .setAutoCancel(true)
                .setChannelId(NOTIFICATION_CHANNEL_ID);
        NotificationManager manager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = builder.build() ;
        if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {
            int importance = NotificationManager. IMPORTANCE_HIGH ;
            NotificationChannel notificationChannel = new NotificationChannel( NOTIFICATION_CHANNEL_ID , "NOTIFICATION_CHANNEL_NAME" , importance) ;
            manager.createNotificationChannel(notificationChannel) ;
        }
        assert manager != null;
        if ( connected ) {
            manager.notify( 1 , notification) ;
            connected = false;
        } else {
            manager.cancel( 1 ) ;
            connected = true;
        }
    }
}