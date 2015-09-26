package com.weaverprojects.imageuploadretrofitphp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by Keith on 2015-09-26.
 */
public class MainRestClient {
    public static MainAPI sBackpackerAPI;

    public static MainAPI get(){
        if(sBackpackerAPI == null){
            setupClient();
        }
        return sBackpackerAPI;
    }
    private static void setupClient(){
        Gson gson = new GsonBuilder().
                registerTypeAdapter(Object.class, new NaturalDeserializer()).
                create();
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(MainAPI.ENDPOINT);
        builder.setClient(new OkClient(getHttpClient()));
        builder.setLogLevel(RestAdapter.LogLevel.FULL);
        builder.setConverter(new GsonConverter(gson));

        RestAdapter restAdapter = builder.build();
        sBackpackerAPI = restAdapter.create(MainAPI.class);
    }
    private static OkHttpClient getHttpClient(){
        OkHttpClient client = new OkHttpClient();
        return client;
    }
}

