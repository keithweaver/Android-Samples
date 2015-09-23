package com.weaverprojects.instagramapi.lib;

import android.os.AsyncTask;
import android.util.Log;

import com.weaverprojects.instagramapi.UI.MainActivity;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Keith on 2015-09-22.
 */
public class LoadStuff extends AsyncTask<String, Void,String> {
        public static final String TAG = "Instagram_Load_";
        @Override
        protected String doInBackground(String... params) {
            String token = params[0];
            try {
                URL url = new URL(C.tokenURLString);
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoInput(true);
                httpsURLConnection.setDoOutput(true);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpsURLConnection.getOutputStream());
                outputStreamWriter.write("client_id=" + C.CLIENT_ID +
                        "&client_secret=" + C.CLIENT_SECRET +
                        "&grant_type=authorization_code" +
                        "&redirect_uri=" + C.CALLBACKURL +
                        "&code=" + token);

                outputStreamWriter.flush();
                //String response = streamToString(httpsURLConnection.getInputStream());
//                    String response = new InputStreamReader(httpsURLConnection.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        httpsURLConnection.getInputStream(), "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "n");
                }
                httpsURLConnection.getInputStream().close();
                String response = sb.toString();
                JSONObject jsonObject = (JSONObject) new JSONTokener(response).nextValue();
                MainActivity.accessTokenString = jsonObject.getString("access_token"); //Here is your ACCESS TOKEN
                MainActivity.id = jsonObject.getJSONObject("user").getString("id");
                MainActivity.username = jsonObject.getJSONObject("user").getString("username"); //This is how you can get the user info. You can explore the JSON sent by Instagram as well to know what info you got in a response
                Log.v(TAG, "AccessToken:[" + MainActivity.accessTokenString + "]");
                Log.v(TAG, "ID:[" + MainActivity.id + "]");
                Log.v(TAG, "User Name:[" + MainActivity.username + "]");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, "Error occurred");
            }
            return "";
        }
}
