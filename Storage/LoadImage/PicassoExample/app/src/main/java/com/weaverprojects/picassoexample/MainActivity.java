package com.weaverprojects.picassoexample;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        String link = "https://scontent.cdninstagram.com/hphotos-xaf1/t51.2885-15/s750x750/sh0.08/e35/11925703_1497132287272542_1657042579_n.jpg";
        Picasso.with(this).load(link).into(imageView);
    }
}
