package com.weaverprojects.toundertest1;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.tounder.tounder.TounderGCMReceiver;


/**
 * Created by Weaver on 15-09-20.
 */
public class GCMIntent extends IntentService {
    public static final String TAG = "TounderTest_";
    int NOTIFICATION_ID = 1;

    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;

    public GCMIntent() {
        super("GCMNotificationIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.v(TAG, "Handle called");
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);

        String messageType = gcm.getMessageType(intent);
        String message = extras.getString("message");
        Log.v(TAG, "Msg:" + message);
        if (!extras.isEmpty()) {
            if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                sendNotification("Send error:" + extras.toString());
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
                sendNotification("Deleted messages on server:" + extras.toString());
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                if (!TextUtils.isEmpty(message)) {
                    sendNotification(message);
                }
            }
        }
        TounderGCMReceiver.completeWakefulIntent(intent);
    }

    private void sendNotification(String msg) {
        mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, new Intent(this, SecondActivity.class), 0);
        android.support.v4.app.NotificationCompat.Builder builder = new NotificationCompat.Builder(this).
                setSmallIcon(R.mipmap.ic_launcher).
                setContentTitle("GCM Example").
                setStyle(new NotificationCompat.BigTextStyle().bigText(msg)).
                setContentText(msg);
        builder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
