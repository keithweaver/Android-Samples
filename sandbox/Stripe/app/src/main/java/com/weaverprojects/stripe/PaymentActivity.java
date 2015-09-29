package com.weaverprojects.stripe;


//import android.os.Bundle;
//import android.support.v4.app.DialogFragment;
//import android.support.v4.app.FragmentActivity;
//
//
//import com.google.android.gms.common.ErrorDialogFragment;
//import com.stripe.android.Stripe;
//
//import com.stripe.android.TokenCallback;
//import com.stripe.android.model.Card;
//import com.stripe.model.Token;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.weaverprojects.stripe.R;
import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.weaverprojects.stripe.ErrorDialogFragment;
import com.weaverprojects.stripe.ProgressDialogFragment;
import com.weaverprojects.stripe.PaymentForm;
import com.weaverprojects.stripe.TokenList;


/**
 * Created by Keith on 2015-09-28.
 */
public class PaymentActivity extends FragmentActivity {
    public static final String TAG = "Stripe_";
    /*
     * Change this to your publishable key.
     *
     * You can get your key here: https://manage.stripe.com/account/apikeys
     */
    public static final String PUBLISHABLE_KEY = "pk_test_19pDQBG066isnDKf95wlLwws";

    private ProgressDialogFragment progressFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressFragment = ProgressDialogFragment.newInstance(R.string.progressMessage);
    }

    public void saveCreditCard(PaymentForm form) {

        Card card = new Card(
                form.getCardNumber(),
                form.getExpMonth(),
                form.getExpYear(),
                form.getCvc());

        boolean validation = card.validateCard();
        if (validation) {
            startProgress();
            new Stripe().createToken(
                    card,
                    PUBLISHABLE_KEY,
                    new TokenCallback() {
                        @Override
                        public void onError(Exception e) {
                            handleError(e.getLocalizedMessage());
                            finishProgress();
                        }

                        @Override
                        public void onSuccess(com.stripe.android.model.Token token) {
                            getTokenList().addToList(token);
                            finishProgress();
                        }
                    });
        } else if (!card.validateNumber()) {
            handleError("The card number that you entered is invalid");
        } else if (!card.validateExpiryDate()) {
            handleError("The expiration date that you entered is invalid");
        } else if (!card.validateCVC()) {
            handleError("The CVC code that you entered is invalid");
        } else {
            handleError("The card details that you entered are invalid");
        }
    }

    private void startProgress() {
        progressFragment.show(getSupportFragmentManager(), "progress");
    }

    private void finishProgress() {
        progressFragment.dismiss();
    }

    private void handleError(String error) {
//        ErrorDialogFragment fragment;
//        fragment = ErrorDialogFragment.newInstance(R.string.validationErrors, error);
//        fragment.show(getSupportFragmentManager(), "error");
        Log.e(TAG, "Error occurred");
        Log.e(TAG, error.toString());
    }

    private TokenList getTokenList() {
        return (TokenList) (getSupportFragmentManager().findFragmentById(R.id.token_list));
    }
}
