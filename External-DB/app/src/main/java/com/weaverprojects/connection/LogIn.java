package com.weaverprojects.connection;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;
import android.util.Log;

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import android.app.Activity;
import android.os.Bundle;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import java.net.URL;
import java.net.URI;

public class LogIn extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        Button login_btn = (Button) findViewById(R.id.button);

        final EditText codeItem = (EditText) findViewById(R.id.code_text);
        final TextView title = (TextView) findViewById(R.id.textView);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pss = codeItem.getText().toString();
                //System.out.println(pss);
                title.setText(pss);
                //System.out.println("WRITE TO CONSOLE --------");
                System.out.println("PASSWORD:[" + pss + "]");


                String link = "http://www.weaverstartup.com/android/db_test.php";
                String result = null;
                InputStream is = null;

                try {
                    //URL url = new URL(link);
                    HttpClient client = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost("http://www.weaverstartup.com/android/db_test.php");

                    //HttpGet request = new HttpGet();
                    //request.setURI(new URI(link));
                    HttpResponse response = client.execute(httppost);

                    HttpEntity entity = response.getEntity();
                    is = entity.getContent();

                    Log.e("log_tag","Connection success");
                    System.out.println("Connection success");


                }catch(Exception e){
                    System.out.println("Unable to connect");
                }

                //-----
                //-- CONVERT RESPOND TO STRING
                //-----

                try{

                }catch(Exception e){
                    System.out.println("Unable to convert");
                }

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_log_in, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
