package com.weaverprojects.instagramapi.UI;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.weaverprojects.instagramapi.R;
import com.weaverprojects.instagramapi.lib.C;
import com.weaverprojects.instagramapi.lib.server.InstagramApp;

public class MainActivity extends Activity {
    public static String TAG = "Insta_MainActivity_";

    InstagramApp mInstagramApp;
    private InstagramWebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInstagramApp = new InstagramApp(this, C.appInfo.CLIENT_ID, C.appInfo.CLIENT_SECRET,
                C.appInfo.CALLBACK_URL);

        final InstagramWebView.OAuthDialogListener listener = new InstagramWebView.OAuthDialogListener() {
            @Override
            public void onComplete(String accessToken) {
                Log.v(TAG, "AccessToken:[" + accessToken + "]");
                mInstagramApp.getAccessToken(accessToken);
            }

            @Override
            public void onError(String error) {
                mInstagramApp.mListener.onFail("Authorizaiton failed.");
                Log.v(TAG, error.toString());
            }
        };



        Button getWebBtn = (Button) findViewById(R.id.getWebBtn);
        getWebBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView = new InstagramWebView(v.getContext(), mInstagramApp.getAuthUrl(), listener);
                mWebView.create();
            }
        });
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
