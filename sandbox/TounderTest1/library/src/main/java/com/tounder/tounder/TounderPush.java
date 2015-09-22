package com.tounder.tounder;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Weaver on 15-09-04.
 */
public class TounderPush {
    String TAG = "TounderPush_";

    GoogleCloudMessaging mGoogleCloudMessaging;

    String SENDER_ID;
    Context context;
    String regId;
    String apiKey;

    String REG_ID_KEY = "regid_key";
    String ACCOUNT;

    String pushNotificationTitle = "tounder.";
    Bitmap pushIcon;

    NotificationManager mNotificationManager;

    public TounderPush(Context context, String senderId, String accountName, String apiKey) {
        super();
        this.context = context;
        this.SENDER_ID = senderId;
        this.ACCOUNT = accountName;
        this.apiKey = apiKey;

        pushIcon = BitmapFactory.decodeResource(
                context.getResources(),
                R.drawable.tounder);
    }

    public void registerDevice(String userName) {
        Log.v(TAG, "Regist device function on tounder push.");
        String temp = getStoreRegisterId();
        if(TextUtils.isEmpty(temp)) {
            Log.v(TAG, "Register in background.");
            registerInBackground();
        }else{
            regId = temp;
        }
        Log.v(TAG, "RegId:[" + regId + "]");
        updateUserOnTounderServer(userName, regId, apiKey);
    }

    private void updateUserOnTounderServer(String userName, String regId, String innerApiKey) {
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                try {
                    String regId = params[0];
                    String userName = params[1];
                    String innerApiKey = params[2];

                    Log.v(TAG, "UpdateUserOnTounder_RegId:[" + regId + "]");
                    Log.v(TAG, "UpdateUserOnTounder_userName:[" + userName + "]");
                    Log.v(TAG, "UpdateUserOnTounder_innerApiKey:[" + innerApiKey + "]");

                    String link = "http://www.tounder.com/server/register.php";

                    String data = URLEncoder.encode("username", "UTF-8") +
                            "=" +
                            URLEncoder.encode(userName, "UTF-8");
                    data += "&";
                    data += URLEncoder.encode("regid", "UTF-8") + "=" +
                            URLEncoder.encode(regId, "UTF-8");
                    data += "&";
                    data += URLEncoder.encode("key", "UTF-8") + "=" +
                            URLEncoder.encode(innerApiKey, "UTF-8");

                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write(data);
                    wr.flush();

                    BufferedReader reader =
                            new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    while ((line = reader.readLine()) != null) {
                        Log.v(TAG,"Line:[" + line + "]");
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                    return "Failed In App";
                }
            }

            @Override
            protected void onPostExecute(String result) {
                Log.v(TAG, "UpdateUserOnTounder_Result:[" + result + "]");
                if (result.contains("Failed")) {
                } else{
                }
            }
        }.execute(regId, userName, apiKey);
    }

    private void registerInBackground() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (mGoogleCloudMessaging == null) {
                        mGoogleCloudMessaging = GoogleCloudMessaging.getInstance(context);
                    }
                    regId = mGoogleCloudMessaging.register(SENDER_ID);
                    Log.v(TAG, "RegId from Register Background:[" + regId + "]");
                    storeRegisterId(regId);
                    msg = "Register Id Successful with " + regId;
                } catch (IOException e) {
                    msg = "Error: Registering GCM Id";
                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {

            }
        }.execute(null, null, null);
    }

    public void storeRegisterId(String reg) {
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences mSP = context.getSharedPreferences(ACCOUNT, mode);
        SharedPreferences.Editor editor = mSP.edit();
        editor.putString(REG_ID_KEY, reg);
        editor.commit();
    }

    public String getStoreRegisterId() {
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences mSP;
        mSP = context.getSharedPreferences(ACCOUNT, mode);
        String regId = mSP.getString(REG_ID_KEY, "");
        return regId;
    }

    public void storeUnregisterId() {
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences mSP;
        mSP = context.getSharedPreferences(ACCOUNT, mode);
        SharedPreferences.Editor editor = mSP.edit();
        editor.putString(REG_ID_KEY, "");
        editor.commit();
    }
    public void sendNotification(String to, String from, String message){
        sendNotificationToServer(to, from, message, apiKey);
    }
    private void sendNotificationToServer(String sendingItTo, String sendingItFrom, String message,
                                          final String apiKey) {
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                try {
                    String sendingItTo = params[0];
                    String sendingItFrom = params[1];
                    String message = params[2];
                    String apiKey = params[3];

                    Log.v(TAG, "To:[" + sendingItTo + "]");
                    Log.v(TAG, "from:[" + sendingItFrom + "]");
                    Log.v(TAG, "Message:[" + message + "]");
                    Log.v(TAG, "API KEY:[" + apiKey + "]");

                    String link = "http://tounder.com/server/send.php";

                    String data = URLEncoder.encode("to", "UTF-8") +
                            "=" + URLEncoder.encode(sendingItTo,"UTF-8");
                    data += "&";
                    data += URLEncoder.encode("from","UTF-8") + "=" +
                            URLEncoder.encode(sendingItFrom, "UTF-8");
                    data += "&";
                    data += URLEncoder.encode("message","UTF-8") + "=" +
                            URLEncoder.encode(message, "UTF-8");
                    data += "&";
                    data += URLEncoder.encode("key","UTF-8") + "=" +
                            URLEncoder.encode(apiKey, "UTF-8");

                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write(data);
                    wr.flush();

                    BufferedReader reader =
                            new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    while((line = reader.readLine()) != null){
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                }catch (Exception e){
                    Log.e(TAG, e.toString());
                    return "Failed In App";
                }
            }

            @Override
            protected void onPostExecute(String result) {
                Log.v(TAG, "result:[" + result + "]");
                if (result.contains("Failed")) {
                } else{
                }
            }
        }.execute(sendingItTo, sendingItFrom, message, apiKey);
    }
    public void handleIntentService(Intent intent, Bundle extras, GoogleCloudMessaging gcm,
                                    PendingIntent contentIntent){
        String messageType = gcm.getMessageType(intent);
        String message = extras.getString("message");
        if (!extras.isEmpty()) {
            if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                sendNotification("Send error:" + extras.toString(), contentIntent);
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
                sendNotification("Deleted messages on server:" + extras.toString(), contentIntent);
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                sendNotification(message, contentIntent);
            }
        }
        TounderGCMReceiver.completeWakefulIntent(intent);
    }
    private void sendNotification(String msg, PendingIntent contentIntent) {
        //Log.v(TAG, "Msg:[" + msg + "]");
        mNotificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        //pending intent was here
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context).
                setLargeIcon(pushIcon).
                setContentTitle(pushNotificationTitle).
                setStyle(new NotificationCompat.BigTextStyle().bigText(msg)).
                setContentText(msg);
        builder.setContentIntent(contentIntent);
        mNotificationManager.notify(1, builder.build());
    }
    public void setPushIcon(Bitmap icon){
        pushIcon = icon;
    }
    public void setPushNotificationTitle(String title){
        pushNotificationTitle = title;
    }
}
