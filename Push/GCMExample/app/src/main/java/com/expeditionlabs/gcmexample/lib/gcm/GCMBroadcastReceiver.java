package com.expeditionlabs.gcmexample.lib.gcm;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

/**
 * Created by Keith on 2015-08-24.
 */
public class GCMBroadcastReceiver extends WakefulBroadcastReceiver {
    public static final String TAG = "WEAVER_GCM_GCMBroadcastReceiver_";
    @Override
    public void onReceive(Context context, Intent intent){
        Log.v(TAG, "OnReceive was called.");
        ComponentName comp = new ComponentName(context.getPackageName(),
                GCMNotificationIntentService.class.getName());
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);
    }
}
