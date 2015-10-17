package com.weaverprojects.instagramapi.lib;

/**
 * Created by Keith on 2015-09-22.
 */
public class C {
    public static final String CLIENT_ID = "5295b1f2e2484d0d851a2715809e2c6b";
    public static final String CLIENT_SECRET = "b310f919514a491ca0e5b2e650c8ed84";
    public static final String REDIRECT_URI = "https://www.weaverstartup.com";

    private static final String AUTHURL = "https://api.instagram.com/oauth/authorize/";
    private static final String TOKENURL = "https://api.instagram.com/oauth/access_token";
    public static final String APIURL = "https://api.instagram.com/v1";
    public static String CALLBACKURL = "Your Redirect URI";

    public static final String authURLString = AUTHURL + "?client_id=" + CLIENT_ID + "&redirect_uri=" + CALLBACKURL + "&response_type=code&display=touch&scope=likes+comments+relationships";
    public static final String tokenURLString = TOKENURL + "?client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&redirect_uri=" + CALLBACKURL + "&grant_type=authorization_code";
}
