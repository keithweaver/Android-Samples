package com.example.weaver.externaldatabase;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.TextView;
import android.util.Log;
import android.app.Activity;
import android.os.Bundle;


import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;





public class MainActivity extends ActionBarActivity {

    TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        resultView = (TextView) findViewById(R.id.result);



    }
    public void getData(){
        String result = "";
        InputStream isr = null;
        try{
           HttpClient httpclient = new DefaultHttpClient();//connect to the http page
           HttpPost httpPost = new HttpPost("http://localhost:8888/android/getPpl.php");//made a post to connect to a website
           HttpResponse response = httpclient.execute(httpPost);//get a json responses
           HttpEntity entity = response.getEntity();//storing it
           isr = entity.getContent();//storing it
        }catch(Exception e){
           Log.e("log_tag", "Error in http connection" + e.toString());
           resultView.setText("Couldnt connect to database");
        }
        //convert response to string
        try{
            BufferedReader reader = new BufferedReader (new InputStreamReader(isr, "iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while((line = reader.readLine())!= null){
                sb.append(line + "\n");
            }
            isr.close();
            result = sb.toString();

        }catch(Exception e){
            Log.e("log_tag","Error converting result " + e.toString() );
        }
        //parse json data
        try{
            String s = "";
            JSONArray jArray = new JSONArray(result);
            for(int i = 0; i< jArray.length(); i++){
                JSONObject json = jArray.getJSONObject(i);
                s = s +
                        "Name : " + json.getString("FirstName") + " " + json.getString("LastName") + "\n" +
                        "Age : " + json.getString("Age") + "\n" +
                        "Mobile Using : " + json.getString("Mobile") + "\n\n";
                System.out.println(s);
            }
            resultView.setText(s);
        }catch(Exception e){
            Log.e("log_tag", "Error Parsing Data " + e.toString());
        }
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
