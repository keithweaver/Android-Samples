package com.weaverprojects.phpbackendtest1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //http://www.helloandroid.com/tutorials/connecting-mysql-database
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("--", "RUNNING----");
        //----------------------------------
        //----------------------------------
        //----------------------------------
        //http://192.186.247.64/android/test.php

        String result = "";
        ArrayList<NameValuePair> namesValuePairs = new ArrayList<>();
        namesValuePairs.add(new BasicNameValuePair("year","1994"));

        //http post
        InputStream is;
        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://www.weaverstartup.com/android/test.php");
            httppost.setEntity(new UrlEncodedFormEntity(namesValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();

            //convert response to string
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null){
                sb.append(line + "\n");
                Log.e("","--- 001");
            }
            is.close();
            result = sb.toString();

            //Parse json data
            JSONArray jArray = new JSONArray(result);
            String labelText = "";
            for(int i=0;i<jArray.length();i++){
                JSONObject json_data = jArray.getJSONObject(i);
                Log.e("ID:", String.valueOf(json_data.getInt("id")));
                Log.e("NAME:", json_data.getString("name"));
                Log.e("BIRTHYEAR:", String.valueOf(json_data.getInt("birthyear")));
                labelText += "|ID:" + String.valueOf(json_data.getInt("id")) + ":NAME:" + json_data.getString("name") + ":BIRTHYEAR:" + String.valueOf(json_data.getInt("birthyear"));
            }
            TextView label = (TextView) findViewById(R.id.text);
            label.setText(labelText);
        }catch(Exception e){

        }




        //----------------------------------
        //----------------------------------
        //----------------------------------
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
