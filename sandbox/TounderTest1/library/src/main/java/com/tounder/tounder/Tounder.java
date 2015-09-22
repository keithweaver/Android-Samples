package com.tounder.tounder;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

/**
 * Created by Weaver on 15-09-04.
 */
public class Tounder {
    String TAG = "Tounder_";

    Context context;
    String senderId;
    String accountName;
    String tounderApiKey;


    TounderPush mTounderPush;

    public Tounder(Context context, String accountName, String tounderApiKey){
        super();
        this.context = context;
        this.senderId = getSenderId(tounderApiKey);
        this.accountName = accountName;
        this.tounderApiKey = tounderApiKey;

        mTounderPush = new TounderPush(context, senderId, accountName, tounderApiKey);
        Log.v(TAG, "Tounder Constructor");
    }
    public void registerThisDeviceForPushNotifications(String userName){
        Log.v(TAG, "Register Device:User:[" + userName + "]");
        mTounderPush.registerDevice(userName);
    }
    public void sendNotificationToServer(String to, String from, String message){
        mTounderPush.sendNotification(to, from, message);
    }
    public void handlePushIntentService(Intent intent, Bundle extras, GoogleCloudMessaging gcm,
                                        PendingIntent contentIntent){
        mTounderPush.handleIntentService(intent, extras, gcm, contentIntent);
    }
    public void setPushNotificationTitle(String t){
        mTounderPush.setPushNotificationTitle(t);
    }
    public void setPushIcon(Bitmap ic){
        mTounderPush.setPushIcon(ic);
    }
    public String getSenderId(String tounderApiKey){
        String id ="";
        for(int i = 0;i < tounderApiKey.length();i++){
            String c = tounderApiKey.substring(i,i+1);
            try{
                int t = Integer.parseInt(c);
                id += c;
            }catch (Exception e){

            }
        }
        Log.v(TAG, "Id:[" + id + "]");
        return id;
    }
}
