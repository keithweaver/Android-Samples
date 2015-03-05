package com.weaverprojects.point;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Weaver on 15-03-05.
 */
public class start extends Activity {
    protected void onCreate(Bundle savedInstanceState){
        /*CHECK FOR LOGIN CODE*/
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences loginSharedPreferences;
        loginSharedPreferences=getSharedPreferences("point_u_t_l",mode);
        boolean loggedIn = loginSharedPreferences.getBoolean("point_u_t_l_state",false);

        super.onCreate(savedInstanceState);
        //if not logged in
        if(loggedIn == true){
            setContentView(R.layout.friends_list);
        }else {
            setContentView(R.layout.start);

            ImageView login_open_btn = (ImageView) findViewById(R.id.login_open_btn);
            login_open_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent("android.intent.action.login"));
                }
            });
            ImageView signup_open_btn = (ImageView) findViewById(R.id.signup_open_btn);
            signup_open_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent("android.intent.action.signup"));
                }
            });
        }
    }
}
