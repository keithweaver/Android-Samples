package com.freecodingtutorials.parseloadinginfo;

import android.content.Context;

import com.parse.ParsePushBroadcastReceiver;
import com.parse.PushService;

/**
 * Created by Weaver on 15-03-24.
 */
public class BroadcastReceiver extends ParsePushBroadcastReceiver {
    public void onReceive(Context context){
        PushService.subscribe(context, "ch1", MainActivity.class);
    }
}
