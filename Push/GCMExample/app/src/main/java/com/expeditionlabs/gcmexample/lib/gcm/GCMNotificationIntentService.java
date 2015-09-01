package com.expeditionlabs.gcmexample.lib.gcm;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;

import com.expeditionlabs.gcmexample.R;
import com.expeditionlabs.gcmexample.UI.SecondActivity;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.util.ArrayList;

/**
 * Created by Keith on 2015-08-24.
 */
public class GCMNotificationIntentService extends IntentService {
    public static final String TAG = "WEAVER_GCM_GCMNotificationIntentService_";
    int NOTIFICATION_ID = 1;

    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;

    GCMDb mGCMDb;

    public GCMNotificationIntentService() {
        super("GCMNotificationIntentService");
        mGCMDb = new GCMDb(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //int NOTIFICATION_ID = 1;
        //Log.v(TAG, "on Handle Intent");

        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);

        String messageType = gcm.getMessageType(intent);
        String fullString = extras.getString("message");

        if(!TextUtils.isEmpty(fullString)) {
            Log.v(TAG, "MessageType:[" + messageType + "]");
            Log.v(TAG, "fullString:[" + fullString + "]");
        }

        if (!extras.isEmpty()) {
            if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                sendNotification("Send error:" + extras.toString());
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
                sendNotification("Deleted messages on server:" + extras.toString());
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                if (!TextUtils.isEmpty(fullString)) {
                    //Log.v(TAG, "not an empty noitification");
                    ArrayList<String> fullStringInList = split(fullString);
                    String message = "";
                    String code = "";
                    for(String item : fullStringInList){
                        Log.v(TAG, "Item:[" + item + "]");
                        if(message.length() == 0){
                            message = item;
                        }else if(code.length() == 0){
                            code = item;
                        }
                    }

                    if(!TextUtils.isEmpty(code)) {
                        Log.v(TAG, "Code:[" + code + "]");
                        Log.v(TAG, "Message[" + message + "]");
                    }

                    if(mGCMDb.codeIsNotUsed(code)){
                        Log.v(TAG, "Code is not used");
                        sendNotification(message);
                        mGCMDb.insert(code, message);
                    }
                }
            }
        }
        //wake up device
        //GCMBroadcastReceiver.startWakefulService(this, intent);
        GCMBroadcastReceiver.completeWakefulIntent(intent);
    }

    private void sendNotification(String msg) {
        Log.v(TAG, "Msg:[" + msg + "]");
        mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, new Intent(this, SecondActivity.class), 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this).
                setSmallIcon(R.mipmap.ic_launcher).
                setContentTitle("GCM Example").
                setStyle(new NotificationCompat.BigTextStyle().bigText(msg)).
                setContentText(msg);
        builder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    protected ArrayList<String> split(String s) {
        ArrayList<String> elements = new ArrayList<String>();
        while (s.contains("|")) {
            //Log.v(TAG, "S:" + s);
            int positionOfLastSplitter = s.lastIndexOf("|");
            //Log.v(TAG, "Position Of Last Splitter:" + positionOfLastSplitter);
            String singleElement = s.substring(positionOfLastSplitter + 1, s.length());
            //Log.v(TAG, "Single Element:" + singleElement);
            s = s.substring(0, positionOfLastSplitter);
            //Log.v(TAG, "New S:" + s);
            elements.add(singleElement);
        }
        //Log.v(TAG, "Final S:" + s);
        elements.add(s);
        return elements;
    }
}
