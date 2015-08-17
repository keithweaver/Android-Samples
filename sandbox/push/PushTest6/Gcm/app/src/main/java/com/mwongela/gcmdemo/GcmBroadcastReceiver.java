package com.mwongela.gcmdemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

/**
 *
 * Created by Moses Mwongela
 * on 7/16/15
 * moses1889@gmail.com
 *
 */

public class GcmBroadcastReceiver extends WakefulBroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		ComponentName comp = new ComponentName(context.getPackageName(), GCMNotificationIntentService.class.getName());
		startWakefulService(context, (intent.setComponent(comp)));
 		setResultCode(Activity.RESULT_OK);
	}
}
