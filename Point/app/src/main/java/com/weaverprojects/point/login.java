package com.weaverprojects.point;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Weaver on 15-03-05.
 */
public class login extends Activity {
    EditText userName;
    EditText password;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        /* NEEDS CHECK LOG IN  CODE*/
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences loginSharedPreferences;
        loginSharedPreferences=getSharedPreferences("point_u_t_l",mode);
        boolean loggedIn = loginSharedPreferences.getBoolean("point_u_t_l_state",false);
        if(loggedIn == true){
            setContentView(R.layout.friends_list);
        }else {
            setContentView(R.layout.login);

            /*LOG IN  CODE GOES HERE */
            Button login_btn = (Button) findViewById(R.id.login_btn);
            login_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    userName = (EditText) findViewById(R.id.login_textbox);
                    password = (EditText) findViewById(R.id.password_textbox);

                    TextView error_label = (TextView) findViewById(R.id.error_label);

                    if(!userName.equals("")){
                        if(!password.equals("")){
                            /*LOG IN GOES HERE */

                        }else{
                            error_label.setText("Please enter a password.");
                        }
                    }else{
                        error_label.setText("Please enter a user name.");
                    }
                }
            });



        }//end of else for the log in window to load
    }
}
