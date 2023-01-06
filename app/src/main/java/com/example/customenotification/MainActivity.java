package com.example.customenotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
private static String CHANNEL = "My Name";
private static final int id = 100;
private static final int REQ_CODE = 50;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bitmap Drawable
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.database, null);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap bitmap = bitmapDrawable.getBitmap();

        // Intent
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // Pending Intent

        PendingIntent pi = PendingIntent.getActivity(this,REQ_CODE,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        // Big Picture Style

        Notification.BigPictureStyle bigPictureStyle = new Notification.BigPictureStyle()
                .bigPicture(bitmap)
                .bigLargeIcon(bitmap)
                .setBigContentTitle("Image Sent By Vishal")
                .setSummaryText("Image Message");

        //Inbox Style

        Notification.InboxStyle inboxStyle = new Notification.InboxStyle()
                .addLine("A")
                .addLine("B")
                .addLine("C")
                .addLine("D")
                .addLine("E")
                .addLine("F")
                .addLine("G")
                .addLine("H")
                .addLine("I")
                .setBigContentTitle("vbishal Pandey")
                .setSummaryText("Hello");

        // notification Manager
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification;

        // 8 version or after 8 version
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(this)
                    .setContentText("Message")
                    .setSubText("Hello vishal Pandey")
                    .setSmallIcon(R.drawable.airplane)
                    .setLargeIcon(bitmap)
                    .setChannelId(CHANNEL)
                    .setStyle(inboxStyle)
                    .setContentIntent(pi)
                    .setOngoing(true)
                    .build();
            nm.createNotificationChannel(new NotificationChannel(CHANNEL,"Hello",NotificationManager.IMPORTANCE_HIGH));
        }

        // before 8 version
        else
        {
            notification = new Notification.Builder(this)
                    .setContentText("Message")
                    .setSubText("Hello vishal Pandey")
                    .setSmallIcon(R.drawable.airplane)
                    .setLargeIcon(bitmap)
                    .setContentIntent(pi)
                    .setStyle(inboxStyle)
                    .setOngoing(true)
                    .build();
        }
        nm.notify(id,notification);
    }
}