package com.weaverprojects.imageuploadretrofitphp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;

import retrofit.Callback;
import retrofit.ResponseCallback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

public class MainActivity extends Activity {
    public static final String TAG = "MainActivity_";

    Button setBtn;
    Button uploadBtn;
    ImageView mainImageView;

    Bitmap uploadImage;
    String realPath;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = this.getApplicationContext();

        setBtn = (Button) findViewById(R.id.setBtn);
        uploadBtn = (Button) findViewById(R.id.uploadBtn);
        mainImageView = (ImageView) findViewById(R.id.mainImage);

        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 0);
            }
        });
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File f = new File(realPath);
                TypedFile typedImage = new TypedFile("multipart/form-data", f);
                uploadImage(typedImage);
            }
        });
    }
    private void uploadImage(TypedFile f){
        MainRestClient.get().postUploadImage(f, new Callback<String>() {
            @Override
            public void success(String res, Response response) {
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "[" + res + "]");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.toString());
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
    protected void showImage(){
        if(uploadImage != null){
            mainImageView.setImageBitmap(uploadImage);
        }
    }
    protected void onActivityResult(int reqCode, int resCode, Intent data){
        if(resCode == Activity.RESULT_OK && data != null){
            //SDK < API11
            if(Build.VERSION.SDK_INT < 11){
                realPath = RealPathUtil.getRealPathFromURI_BelowAPI11(this, data.getData());
            }else if(Build.VERSION.SDK_INT < 19){
                realPath = RealPathUtil.getRealPathFromURI_API11to18(this, data.getData());
            }else{
                realPath = RealPathUtil.getRealPathFromURI_API19(this, data.getData());
            }
            path = data.getData().getPath().toString();

            getFileFromBitmap();
            showImage();
        }
    }
    protected void getFileFromBitmap(){
        if(path != null && realPath != null){
            Uri uriPath = Uri.fromFile(new File(realPath));
            try {
                uploadImage = BitmapFactory.decodeStream(getContentResolver().openInputStream(uriPath));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
