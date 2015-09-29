package com.weaverprojects.stripe2.View;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.stripe.android.model.Card;
import com.weaverprojects.stripe2.Controller.Stripe.StripeManager;
import com.weaverprojects.stripe2.Model.CreditCard;
import com.weaverprojects.stripe2.R;

public class PaymentActivity extends Activity {
    public static final String TAG = "Stripe_PaymentActivity_";

    Button buyBtn;
    EditText card1EditText;
    EditText card2EditText;
    EditText card3EditText;
    EditText card4EditText;

    EditText monthEditText;
    EditText yearEditText;

    EditText cvcEditText;

    EditText dollarEditText;
    EditText centEditText;

    EditText userEditText;
    EditText currencyEditText;


    StripeManager mStripeManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        //Declare UI Objects
        buyBtn = (Button) findViewById(R.id.buyBtn);
        card1EditText = (EditText) findViewById(R.id.card1);
        card2EditText = (EditText) findViewById(R.id.card2);
        card3EditText = (EditText) findViewById(R.id.card3);
        card4EditText = (EditText) findViewById(R.id.card4);

        monthEditText = (EditText) findViewById(R.id.month);
        yearEditText = (EditText) findViewById(R.id.year);

        cvcEditText = (EditText) findViewById(R.id.cvc);

        dollarEditText = (EditText) findViewById(R.id.dollar);
        centEditText = (EditText) findViewById(R.id.cent);

        userEditText = (EditText) findViewById(R.id.user);
        currencyEditText = (EditText) findViewById(R.id.currency);

        mStripeManager = new StripeManager(this);

        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String card1Str = card1EditText.getText().toString();
                String card2Str = card2EditText.getText().toString();
                String card3Str = card3EditText.getText().toString();
                String card4Str = card4EditText.getText().toString();

                String monthStr = monthEditText.getText().toString();
                String yearStr = yearEditText.getText().toString();

                String cvcStr = cvcEditText.getText().toString();

                String dollarStr = dollarEditText.getText().toString();
                String centsStr = centEditText.getText().toString();

                String user = userEditText.getText().toString();
                String currency = currencyEditText.getText().toString();

                if(centsStr.length() == 0){
                    centsStr = "00";
                }else if(centsStr.length() == 1){
                    centsStr = "0" + centsStr;
                }
                if((!TextUtils.isEmpty(centsStr)) &&
                        (!TextUtils.isEmpty(user)) &&
                        (!TextUtils.isEmpty(currency)) &&
                        (!TextUtils.isEmpty(cvcStr)) &&
                        (!TextUtils.isEmpty(monthStr)) &&
                        (!TextUtils.isEmpty(yearStr)) &&
                        (!TextUtils.isEmpty(card1Str)) &&
                        (!TextUtils.isEmpty(card2Str)) &&
                        (!TextUtils.isEmpty(card3Str)) &&
                        (!TextUtils.isEmpty(card4Str))){
                    try{
                        int total = Integer.parseInt(dollarStr + centsStr);

                        int month = Integer.parseInt(monthStr);
                        int year = Integer.parseInt(yearStr);

                        String num = card1Str + card2Str + card3Str + card4Str;

                        CreditCard c = new CreditCard(num, month, year, cvcStr);

                        mStripeManager.charge(c, total, currency, user,"Bought on Android");
                    }catch (Exception e){
                        Log.e(TAG, e.toString());
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_payment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
