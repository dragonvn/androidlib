package com.example.duchv.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by duchv on 2/6/15.
 */
public class MyNotificationService extends Service {
    @Override
    public IBinder onBind( Intent itent){
        return null;

    }

    @Override
    public void onCreate() {
        super.onCreate();

        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationManager nNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(this.getApplicationContext(),MainActivity.class);
        PendingIntent pItent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification notify = new Notification.Builder(this)
                .setContentTitle("Wake up!")
                .setContentText("Good Luck for you")
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentIntent(pItent)
                .setSound(sound)
                .addAction(0,"Alarm Notifi",pItent)
                .build();
        nNM.notify(1,notify);
        Log.d("DEBUGALARM","OOOOOOOOOOOOOOOOOYES");
    }
}
