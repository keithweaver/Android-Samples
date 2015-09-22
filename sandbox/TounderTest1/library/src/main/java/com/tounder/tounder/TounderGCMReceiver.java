package com.tounder.tounder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;



/**
 * Created by Weaver on 15-09-05.
 */
public class TounderGCMReceiver extends WakefulBroadcastReceiver {
    public static final String TAG = "WEAVER_GCM_GCMBroadcastReceiver_";
    @Override
    public void onReceive(Context context, Intent intent){

        ComponentName comp = new ComponentName(context.getPackageName(),
                "GCMNotificationIntentService");
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);
    }
}