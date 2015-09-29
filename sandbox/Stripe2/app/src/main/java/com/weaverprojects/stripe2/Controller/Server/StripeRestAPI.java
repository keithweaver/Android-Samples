package com.weaverprojects.stripe2.Controller.Server;

import com.stripe.android.model.Token;

import retrofit.ResponseCallback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Keith on 2015-09-28.
 */
public interface StripeRestAPI {
    String ENDPOINT = "https://www.weaverstartup.com/";

    @FormUrlEncoded
    @POST("charge.php")
    void postCharge(@Field("stripeToken") Token token,
                    @Field("amount") int amount,
                    @Field("currency") String currency,
                    @Field("user") String user,
                    @Field("msg") String msg,
                    ResponseCallback callback);
}
