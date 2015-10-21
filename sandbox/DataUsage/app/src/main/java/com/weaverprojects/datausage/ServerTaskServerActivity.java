package com.weaverprojects.datausage;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by kweaver on 31/07/15.
 */
public class ServerTaskServerActivity extends AsyncTask<String, Void, String> {
    public static String TAG = "OpenInstagram_CheckCredentialsForLogInServerActivity_";

    private Context context;

    public ServerTaskServerActivity (Context c){
        this.context = c;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            String username = params[0];


            String link="http://weaverprojects.com/instagram/loadposts.php";
            String data  = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode("kweaver0", "UTF-8");
            data += "&" + URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode("SOME_HIDDEN_CODE", "UTF-8");

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write( data );
            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                sb.append(line);
                break;
            }
            return sb.toString();
        }catch(Exception e){
            Log.v(TAG, e.toString());
            return "Failed InApp 001";
        }
    }
    @Override
    protected void onPostExecute(String result){
        Log.v(TAG, "OnPostExecute  Result:" + result);
        if(result.contains("Failed")){
            Toast.makeText(context, "Failed to sign in.", Toast.LENGTH_SHORT).show();
            MainActivity.error = true;
        }else{
            MainActivity.error = false;
            MainActivity.successfulTasks++;
        }
    }
}