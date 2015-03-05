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
public class signup extends Activity {
    EditText userName;
    EditText password;
    EditText first;
    EditText last;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        int mode = Activity.MODE_PRIVATE;
        SharedPreferences loginSharedPreferences;
        loginSharedPreferences=getSharedPreferences("point_u_t_l",mode);
        boolean loggedIn = loginSharedPreferences.getBoolean("point_u_t_l_state",false);
        if(loggedIn == true){

        }else {
            setContentView(R.layout.signup);


            Button sign_up_btn = (Button) findViewById(R.id.sign_up_btn);
            sign_up_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /* USER SIGN UP GOES IN HERE */
                    userName = (EditText) findViewById(R.id.login_textbox);
                    first = (EditText) findViewById(R.id.first_name_textbox);
                    last = (EditText) findViewById(R.id.last_name_textbox);
                    password = (EditText) findViewById(R.id.password_textbox);

                    TextView error_label = (TextView) findViewById(R.id.error_label);

                    if (!first.equals("")) {
                        //last name can be empty

                        //NEEDS: Check if username is taken
                        if (!userName.equals("")) {
                            if (!password.equals("") || password.length() < 3) {
                                //passes


                                //NEED TO ADD DB STUFF

                                /* LOG IN CODE */
                                int mode = Activity.MODE_PRIVATE;
                                SharedPreferences loginSharedPreferences;
                                loginSharedPreferences = getSharedPreferences("point_u_t_l", mode);
                                SharedPreferences.Editor editor = loginSharedPreferences.edit();
                                editor.putBoolean("point_u_t_l_state", true);
                                editor.putString("point_user", String.valueOf(userName));
                                editor.commit();

                            } else {
                                //present password is empty
                                //error_label
                                error_label.setText("Password is less than 3 characters.");
                            }
                        } else {
                            //present error saying user name is empty
                            error_label.setText("User name is empty.");
                        }
                    } else {
                        //present error saying first name is empty
                        error_label.setText("First name is empty.");
                    }

                }
            });
        }//end of else for the sign up window to load

    }
}
