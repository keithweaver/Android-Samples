package com.expeditionlabs.gcmexample.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.expeditionlabs.gcmexample.R;
import com.expeditionlabs.gcmexample.db.RegisterUserNameServerActivity;
import com.expeditionlabs.gcmexample.lib.C;
import com.expeditionlabs.gcmexample.lib.gcm.RegisterCommon;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
    public static final String TAG = "WEAVER_GCM_MainActivity_";
    Context mContext;
    String regId;
    RegisterCommon mRegisterCommon;

    GoogleCloudMessaging mGoogleCloudMessaging;

    String u = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this.getApplicationContext();
        Log.v(TAG, "Opening");
        mRegisterCommon = new RegisterCommon(mContext);
        regId = mRegisterCommon.getStoreRegisterId();
        Log.v(TAG, "RegId:[" + regId + "]");
        if (regId != null && (!TextUtils.isEmpty(regId))) {
            Log.v(TAG, "Redirect.");
            Intent openSecondWindow = new Intent(this, SecondActivity.class);
            startActivity(openSecondWindow);
        }
        setContentView(R.layout.activity_main);


        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.editText);
                String userNameFromTextbox = editText.getText().toString();
                userNameFromTextbox = userNameFromTextbox.trim();
                userNameFromTextbox = userNameFromTextbox.toLowerCase();
                if (userNameFromTextbox.length() > 0) {
                    if (TextUtils.isEmpty(regId)) {
                        registerInBackground();
                    }
                    u = userNameFromTextbox;
                }

                editText.setText("");
            }
        });

        final Timer mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            public void run() {
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.v(TAG, "RegId 2:[" + regId + "]");
                            if (!TextUtils.isEmpty(regId)) {
                                Log.v(TAG, "Firing...");
                                new RegisterUserNameServerActivity(mContext).execute(regId, u);

                                Intent openSecondWindow = new Intent(mContext, SecondActivity.class);
                                openSecondWindow.putExtra(C.extras.USERNAME, u);
                                openSecondWindow.putExtra(C.extras.REG_ID, regId);

                                mTimer.cancel();

                                startActivity(openSecondWindow);
                            }
                        }
                    });
                } catch (Exception e) {
                    Log.v(TAG, "Error in timer");
                    Log.e(TAG, e.toString());
                }
            }
        }, 1000, 1000);
    }

    private void registerInBackground() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (mGoogleCloudMessaging == null) {
                        mGoogleCloudMessaging = GoogleCloudMessaging.getInstance(mContext);
                    }
                    regId = mGoogleCloudMessaging.register(C.google.SENDER_ID);
                    mRegisterCommon.storeRegisterId(regId);
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


}
