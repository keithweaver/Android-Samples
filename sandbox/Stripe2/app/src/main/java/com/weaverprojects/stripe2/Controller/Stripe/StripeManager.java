package com.weaverprojects.stripe2.Controller.Stripe;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.weaverprojects.stripe2.Controller.C;
import com.weaverprojects.stripe2.Controller.Server.StripeRestAPI;
import com.weaverprojects.stripe2.Controller.Server.StripeRestClient;
import com.weaverprojects.stripe2.Model.CreditCard;

import retrofit.ResponseCallback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Keith on 2015-09-28.
 */
public class StripeManager {
    public static final String TAG = "Stripe_Manage_";
    Context context;
    public StripeManager(Context c){
        this.context = c;
    }

    public void charge(CreditCard c, final int amount, final String currency, final String user, final String msg){
        Log.v(TAG, "Charge");
        Card card = new Card(
                c.getNumber(),
                c.getMonth(),
                c.getYear(),
                c.getCvc());
        boolean validation = card.validateCard();
        if(validation){
            Log.v(TAG, "Valid card");
            new Stripe().createToken(card, C.Stripe.PUBLISHABLE_KEY, new TokenCallback() {
                @Override
                public void onError(Exception error) {
                    Log.e(TAG, "Error with getting stripe token");
                    Log.e(TAG, error.toString());
                }

                @Override
                public void onSuccess(Token token) {
                    Log.v(TAG, "Retrieved token");
                    Log.v(TAG, "Token:[" + token.getId() + "]");
                    sendChargeToServer(token, amount, currency, user, msg);
                }
            });
        }
    }
    private void sendChargeToServer(Token token, int amount, String currency, String user, String msg){
        StripeRestClient.get().postCharge(token, amount, currency, user, msg, new ResponseCallback() {
            @Override
            public void success(Response response) {
                Toast.makeText(context, "Payment complete", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(context, "Payment error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
