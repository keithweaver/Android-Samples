package com.weaverprojects.imageuploadretrofitphp;

import retrofit.Callback;
import retrofit.ResponseCallback;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.mime.TypedFile;

/**
 * Created by Keith on 2015-09-26.
 */
public interface MainAPI {
    String ENDPOINT = "https://www.weaverstartup.com";

    @Multipart
    @POST("/upload.php")
    void postUploadImage(@Part("imgFile")TypedFile file, Callback<String> callback);
}
