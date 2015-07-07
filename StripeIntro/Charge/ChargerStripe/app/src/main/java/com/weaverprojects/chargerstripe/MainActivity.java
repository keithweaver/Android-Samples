package com.weaverprojects.chargerstripe;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.stripe.*;
import com.stripe.exception.AuthenticationException;
import com.stripe.model.Card;
import com.stripe.model.Token;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.chargeBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText card1TextField = (EditText) findViewById(R.id.card1);
                EditText card2TextField = (EditText) findViewById(R.id.card2);
                EditText card3TextField = (EditText) findViewById(R.id.card3);
                EditText card4TextField = (EditText) findViewById(R.id.card4);

                String card1 = card1TextField.getText().toString();
                String card2 = card2TextField.getText().toString();
                String card3 = card3TextField.getText().toString();
                String card4 = card4TextField.getText().toString();

                if((!card1.isEmpty()) && (!card2.isEmpty()) && (!card3.isEmpty()) &&
                        (!card4.isEmpty())){
                    String cardNumber = card1 + card2 + card3 + card4;


                }
            }
        });
    }

    protected void test() throws AuthenticationException {
        Card card = new Card("4242424242424242", "12", "2014", "123");

        Stripe stripe = new Stripe("pk_test_6pRNASCoBOKtIshFeQd4XMUh");
        stripe.createToken(
                card,
                new TokenCallback() {
                    public void onSuccess(Token token) {
                        // Send token to your server
                    }
                    public void onError(Exception error) {
                        // Show localized error message
                        //Toast.makeText(this.getApplicationContext())
                    }
                }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
