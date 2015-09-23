package com.weaverprojects.instagramapi.lib.server;

import com.weaverprojects.instagramapi.lib.server.Model.Res_UserInfo;


import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Keith on 2015-09-22.
 */
public interface InstaAPI {
    String ENDPOINT = "https://api.instagram.com/";

//    @POST("oauth/authorize/")
//    void getAuthorization(@Field("client_id") String clientId, @Field("client_secret") String clientSecret, @Field("redirect_uri") String redirectURI, @Field("code") String code, Callback<Res_UserInfo> callback);

    @POST("oauth/access_token")
    void getAccessToken(@Field("client_id") String clientId, @Field("client_secret") String clientSecret, @Field("redirect_uri") String redirect_uri, @Field("grant_type") String grantType, @Field("code") String code, Callback<Res_UserInfo> callback);

    ///@POST("v1/users/")
}