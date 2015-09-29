package com.weaverprojects.stripe2.Controller.Server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by Keith on 2015-09-28.
 */
public class StripeRestClient {
    public static StripeRestAPI sStripeRestAPI;

    public static StripeRestAPI get(){
        if(sStripeRestAPI == null){
            setupClient();
        }
        return sStripeRestAPI;
    }
    private static void setupClient(){
        Gson gson = new GsonBuilder().
                registerTypeAdapter(Object.class, new NaturalDeserializer()).
                create();
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(StripeRestAPI.ENDPOINT);
        builder.setClient(new OkClient(getHttpClient()));
        builder.setLogLevel(RestAdapter.LogLevel.FULL);
        builder.setConverter(new GsonConverter(gson));

        RestAdapter restAdapter = builder.build();
        sStripeRestAPI = restAdapter.create(StripeRestAPI.class);
    }
    private static OkHttpClient getHttpClient(){
        OkHttpClient client = new OkHttpClient();
        return client;
    }
}
