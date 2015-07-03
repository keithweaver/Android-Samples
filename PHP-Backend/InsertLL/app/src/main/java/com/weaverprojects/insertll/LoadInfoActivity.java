package com.weaverprojects.insertll;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Weaver on 15-07-02.
 */
public class LoadInfoActivity extends AsyncTask<String,Void,String> {
    private Context context;
    private TextView textview;
    public LoadInfoActivity(Context c, TextView t){
        this.context = c;
        this.textview = t;
    }

    @Override
    protected String doInBackground(String... params) {
        try{
            String latitude = (String)params[0];
            String longitude = (String)params[1];

            String link = "http://weaverstartup.com/android/location.php";
            String data = URLEncoder.encode("latitude","UTF-8") + "=" + URLEncoder.encode(latitude, "UTF-8");
            data += "&" + URLEncoder.encode("longitude", "UTF-8") + "=" + URLEncoder.encode(longitude, "UTF-8");

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write(data);
            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

            while((line = reader.readLine()) != null){
                sb.append(line);
                break;
            }
            return sb.toString();
        }catch (Exception e){
            Log.e("LoadInfoActivity_", e.toString());
            MainActivity.textView.setText("Error\n" + e.toString());
        }
        return null;
    }
    @Override
    protected void onPostExecute(String result){
        this.textview.setText(result);
    }

}
