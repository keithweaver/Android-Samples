package com.expeditionlabs.gcmexample.UI;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.expeditionlabs.gcmexample.R;
import com.expeditionlabs.gcmexample.db.SendMessageServerActivity;
import com.expeditionlabs.gcmexample.lib.C;
import com.expeditionlabs.gcmexample.lib.gcm.GCMDb;
import com.expeditionlabs.gcmexample.lib.gcm.GCMNotificationIntentService;
import com.expeditionlabs.gcmexample.lib.gcm.RegisterCommon;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

/**
 * Created by Keith on 2015-08-24.
 */
public class SecondActivity extends Activity {
    public static final String TAG = "WEAVER_GCM_SecondActivity_";
    String USERNAME_LABEL = "User Name:";
    String REG_ID_LABEL = "REG ID:";

    TextView userNameLabel;
    TextView regIdLabel;

    EditText msgTxt;
    Button sendBtn;
    Button unregBtn;

    String regId;
    String userName;

    GCMDb mGCMDb;

    GoogleCloudMessaging mGoogleCloudMessaging;

    Context mContext;
    RegisterCommon mRegisterCommon;

    int NOTIFICATION_ID = 1;

    private NotificationManager mNotificationManager;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mContext = this.getApplicationContext();

        userNameLabel = (TextView) findViewById(R.id.userNameLabel);
        regIdLabel = (TextView) findViewById(R.id.regIdLabel);

        Intent openWindow = getIntent();
        userName = openWindow.getStringExtra(C.extras.USERNAME);
        regId = openWindow.getStringExtra(C.extras.REG_ID);

        regIdLabel.setText(REG_ID_LABEL + "[" + regId + "]");
        userNameLabel.setText(USERNAME_LABEL + "[" + userName + "]");

        msgTxt = (EditText) findViewById(R.id.msgTxt);
        sendBtn = (Button) findViewById(R.id.sendBtn);
        unregBtn = (Button) findViewById(R.id.unregBtn);

        mGCMDb = new GCMDb(this);
        mGoogleCloudMessaging = GoogleCloudMessaging.getInstance(mContext);
        mRegisterCommon = new RegisterCommon(mContext);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = msgTxt.getText().toString();
                new SendMessageServerActivity(v.getContext()).execute(userName, msg);
                msgTxt.setText("");
            }
        });
        unregBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification("Logged out");
                mGCMDb.deleteAllRows();
                Log.v(TAG, "Rows:" + mGCMDb.numberOfRows());
                unregisterInBackground();


            }
        });
    }
    private void unregisterInBackground() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (mGoogleCloudMessaging == null) {
                        mGoogleCloudMessaging = GoogleCloudMessaging.getInstance(mContext);
                    }
                    regId = mGoogleCloudMessaging.register(C.google.SENDER_ID);
                    mRegisterCommon.storeUnregisterId();
                    msg = "Unregister Id Successful with " + regId;
                    Log.v(TAG, "Unregistered");
                    //Toast.makeText(mContext, "Unregistered", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    msg = "Error: Unregistering GCM Id";
                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {

            }
        }.execute(null, null, null);
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
}
