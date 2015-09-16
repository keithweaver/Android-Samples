package com.freecodingtutorials.loadimagefromweb;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
    Button loadBtn;
    public static ImageView mainImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainImageView = (ImageView) findViewById(R.id.mainImageView);
        loadBtn = (Button) findViewById(R.id.loadBtn);

        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoadImage(v.getContext()).execute("https://scontent.cdninstagram.com/hphotos-xaf1/t51.2885-15/s750x750/sh0.08/e35/11925703_1497132287272542_1657042579_n.jpg");
            }
        });
    }
}
