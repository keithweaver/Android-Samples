package com.weaverprojects.working_w_tut;

import android.app.Application;
import com.parse.Parse;
import com.parse.PushService;

/**
 * Created by Weaver on 15-02-20.
 */
public class ParsePushApplication extends Application{
    public void onCreate(){
        Parse.initialize(this, getString(R.string.parseAppId), getString(R.string.parseClientId));
        PushService.setDefaultPushCallback(this, MainActivity.class);//when clicking notification, opens this page
    }
}
