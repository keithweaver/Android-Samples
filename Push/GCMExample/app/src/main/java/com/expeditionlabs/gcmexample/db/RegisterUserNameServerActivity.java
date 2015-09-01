package com.expeditionlabs.gcmexample.db;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.expeditionlabs.gcmexample.lib.C;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Keith on 2015-08-24.
 */
public class RegisterUserNameServerActivity extends AsyncTask<String, Void, String> {
    public static String TAG = "WEAVER_GCM_RegisterUserNameServerActivity_";

    private Context context;
    public RegisterUserNameServerActivity(Context c){
        this.context = c;
    }
    @Override
    protected String doInBackground(String...params){
        try{
            String regId = params[0];
            String userName = params[1];
            Log.v(TAG, "RegId:[" + regId + "]");
            Log.v(TAG, "User Name:[" + userName + "]");

            String link = C.db.MAIN_LINK + "/" + C.db.RegisterUserName.PAGE;

            String data = URLEncoder.encode(C.db.RegisterUserName.USERNAME_KEY,"UTF-8") +
                    "=" + URLEncoder.encode(userName,"UTF-8");
            data += "&";
            data += URLEncoder.encode(C.db.RegisterUserName.REG_ID_KEY,"UTF-8") + "=" +
                    URLEncoder.encode(regId, "UTF-8");

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
            Log.e(TAG, e.toString());
            return "Failed In App";
        }
    }
    @Override
    protected void onPostExecute(String result){
        Log.v(TAG, "Result:[" + result + "]");
        if(result.contains("Failed")){

        }else{

        }
    }
}
