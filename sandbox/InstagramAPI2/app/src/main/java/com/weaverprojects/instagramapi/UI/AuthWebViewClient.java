package com.weaverprojects.instagramapi.UI;

import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.weaverprojects.instagramapi.lib.C;
import com.weaverprojects.instagramapi.lib.LoadStuff;

/**
 * Created by Keith on 2015-09-22.
 */
public class AuthWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url.startsWith(C.CALLBACKURL)) {
            System.out.println(url);
            String parts[] = url.split("=");
              //This is your request token.
            //InstagramLoginDialog.this.dismiss();
            String request_token = parts[1];
            Log.v("Instagram_Web_", "Request:[" + request_token + "]");
            new LoadStuff().execute(request_token);
            return true;
        }
        return false;
    }
}
