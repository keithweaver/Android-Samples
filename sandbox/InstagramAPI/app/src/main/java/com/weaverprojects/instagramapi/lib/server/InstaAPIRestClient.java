package com.weaverprojects.instagramapi.lib.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by Keith on 2015-09-22.
 */
public class InstaAPIRestClient {
    private static InstaAPI sInstaAPI;

    public static InstaAPI get(){
        if(sInstaAPI == null){
            setupClient();
        }
        return sInstaAPI;
    }
    private static void setupClient(){
        Gson gson = new GsonBuilder().
                registerTypeAdapter(Object.class, new NaturalDeserializer()).
                create();
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(InstaAPI.ENDPOINT);
        builder.setClient(new OkClient(getHttpClient()));
        builder.setLogLevel(RestAdapter.LogLevel.FULL);
        builder.setConverter(new GsonConverter(gson));

        RestAdapter restAdapter = builder.build();
        sInstaAPI = restAdapter.create(InstaAPI.class);
    }
    private static OkHttpClient getHttpClient(){
        OkHttpClient client = new OkHttpClient();
        return client;
    }
}
