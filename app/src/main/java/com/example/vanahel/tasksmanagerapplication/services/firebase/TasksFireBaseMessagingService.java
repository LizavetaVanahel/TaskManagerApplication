package com.example.vanahel.tasksmanagerapplication.services.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;

import com.example.vanahel.tasksmanagerapplication.NewTaskActivity;
import com.example.vanahel.tasksmanagerapplication.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class TasksFireBaseMessagingService extends FirebaseMessagingService {

    public static final String INTENT_FILTER = "INTENT_FILTER";
    public static final String BROADCAST_RECEIVER_INTENT = "brodcastreceiver";
    private final static String COMMA = ",";
    private String[] notificationTitleDesc = new String[2];
    private String title;
    private String description;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        String notificationTitle = null, notificationBody = null;

        if (remoteMessage.getNotification() != null) {
            notificationTitle = remoteMessage.getNotification().getTitle();
            notificationBody = remoteMessage.getNotification().getBody();
            remoteMessage.getData().values();
            for (String titleAndDesc : remoteMessage.getData().values()) {
                notificationTitleDesc = titleAndDesc.split(COMMA);
            }
            title = notificationTitleDesc[0];
            description = notificationTitleDesc[1];
            Intent intent = new Intent(INTENT_FILTER);
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

        }

        sendNotification(notificationTitle, notificationBody);
    }

    private void sendNotification(String notificationTitle, String notificationBody) {

        Intent intent = new Intent(this, NewTaskActivity.class);
        intent.putExtra(BROADCAST_RECEIVER_INTENT, "brodcastreceiver");
        intent.putExtra("title", title);
        intent.putExtra("description", description);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setContentTitle(notificationTitle)
                .setContentText(notificationBody)
                .setSound(defaultSoundUri);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }
}

