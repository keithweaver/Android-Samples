package com.freecodingtutorials.loadimagefromweb;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Keith on 2015-09-15.
 */
public class LoadImage extends AsyncTask<String,String,Bitmap>{
    public static final String TAG = "";

    Context context;
    Bitmap bitmap;

    public LoadImage(Context c){
        super.onPreExecute();
        context = c;
    }
    public Bitmap doInBackground(String... args){
        String link = args[0];
        try{
            bitmap = BitmapFactory.decodeStream((InputStream) new URL(link).getContent());
        }catch (Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }
    public void onPostExecute(Bitmap image){
        if(image != null){
            MainActivity.mainImageView.setImageBitmap(image);
        }
    }
}
