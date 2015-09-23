package com.weaverprojects.instagramapi.lib.server;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.weaverprojects.instagramapi.UI.InstagramWebView;
import com.weaverprojects.instagramapi.lib.server.Model.Res_UserInfo;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Keith on 2015-09-22.
 */
public class InstagramApp {
    public static String TAG = "Instagram_";

    private InstagramSession mSession;
    private String mClientId;
    private String mClientSecret;
    private Context mContext;
    public static String mCallbackUrl = "";



    public OAuthAuthenticationListener mListener;
    private String mAuthUrl;

    private static int WHAT_FINALIZE = 0;
    private static int WHAT_ERROR = 1;
    private static int WHAT_FETCH_INFO = 2;

    public InstagramApp(Context c, String clientId, String clientSecret, String callBackUrl){
        mContext = c;
        mClientId = clientId;
        mClientSecret = clientSecret;
        mCallbackUrl = callBackUrl;
        mSession = new InstagramSession(mContext);

        mAuthUrl = "https://api.instagram.com/oauth/authorize/" + "?client_id=" + clientId + "&redirect_uri="
                + mCallbackUrl + "&response_type=code&display=touch&scope=likes+comments+relationships";


    }
    public void getAccessToken(final String code){
        //mSession.storeAccessToken(mAccessToken, id, user, name);
        getAccessToken(mClientId, mClientSecret, mCallbackUrl, "authorization_code", code);
        int what = WHAT_FETCH_INFO;
        mHandler.sendMessage(mHandler.obtainMessage(what, 1, 0));
    }
    private void getAccessToken(String clientId, String clientSecret, String redirect_uri, String grantType, String code){
        InstaAPIRestClient.get().getAccessToken(clientId, clientSecret, redirect_uri, grantType, code, new Callback<Res_UserInfo>() {
            @Override
            public void success(Res_UserInfo res, Response response) {
                if(res.getUsername() != null) {
                    Log.v(TAG, "UserName:" + res.getUsername());
                }
                Log.v(TAG, "Id:" + res.getId());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error on access token");
                Log.e(TAG, error.toString());
            }
        });
    }
    public String getAuthUrl(){
        return mAuthUrl;
    }
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == WHAT_ERROR) {
//                mProgress.dismiss();
                if(msg.arg1 == 1) {
                    mListener.onFail("Failed to get access token");
                }
                else if(msg.arg1 == 2) {
                    mListener.onFail("Failed to get user information");
                }
            }
            else if(msg.what == WHAT_FETCH_INFO) {
                fetchUserName();
            }
            else {
                //mProgress.dismiss();
                mListener.onSuccess();
            }
        }
    };
    private void fetchUserName(){

    }
    public interface OAuthAuthenticationListener{
        public abstract void onSuccess();
        public abstract void onFail(String error);
    }
}
