package com.expeditionlabs.gcmexample.db;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.expeditionlabs.gcmexample.lib.C;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Keith on 2015-08-25.
 */
public class SendMessageServerActivity extends AsyncTask<String, Void, String> {
    public static String TAG = "WEAVER_GCM_SendMessageServerActivity_";

    private Context context;
    public SendMessageServerActivity(Context c){
        this.context = c;
    }
    @Override
    protected String doInBackground(String...params){
        try{

            String userName = params[0];
            String message = params[1];
            Log.v(TAG, "User Name:[" + userName + "]");
            Log.v(TAG, "Message:[" + message + "]");

            String link = C.db.MAIN_LINK + "/" + C.db.SendMessage.PAGE;

            String data = URLEncoder.encode(C.db.SendMessage.USERNAME_KEY, "UTF-8") +
                    "=" + URLEncoder.encode(userName,"UTF-8");
            data += "&";
            data += URLEncoder.encode(C.db.SendMessage.MESSAGE_KEY,"UTF-8") + "=" +
                    URLEncoder.encode(message, "UTF-8");

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write(data);
            wr.flush();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

            while((line = reader.readLine()) != null){
                sb.append(line);
                break;
            }
            return sb.toString();
        }catch (Exception e){
            return "Failed In App";
        }
    }
    @Override
    protected void onPostExecute(String result){
        Log.v(TAG, "Result:[" + result + "]");
        if(result.contains("Failed")){
            Toast.makeText(context, "Error sending", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Sent", Toast.LENGTH_SHORT).show();
        }
    }
}