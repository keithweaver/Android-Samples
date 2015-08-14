package com.weaverprojects.swipabletest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.andtinder.model.CardModel;
import com.andtinder.model.Orientations;
import com.andtinder.view.CardContainer;
import com.andtinder.view.SimpleCardStackAdapter;


public class MainActivity extends Activity {
    CardContainer mCardContainer;

    int mTopCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCardContainer = (CardContainer) findViewById(R.id.layoutview);
        mCardContainer.setOrientation(Orientations.Orientation.Ordered);
        //CardModel card = new CardModel("Title1", "Description goes here", r.getDrawable(R.drawable.picture1);
        SimpleCardStackAdapter adapter = new SimpleCardStackAdapter(this);
        adapter.add(new CardModel("Title1", "Description goes here", getResources().getDrawable(R.drawable.picture1)));
        adapter.add(new CardModel("Title2", "Description goes here", getResources().getDrawable(R.drawable.picture1)));
        adapter.add(new CardModel("Title3", "Description goes here", getResources().getDrawable(R.drawable.picture1)));
        adapter.add(new CardModel("Title4", "Description goes here", getResources().getDrawable(R.drawable.picture1)));
        adapter.add(new CardModel("Title5", "Description goes here", getResources().getDrawable(R.drawable.picture1)));
        mCardContainer.setAdapter(adapter);

        mTopCard = 4;
        /*
        mCardContainer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemValue = (String) mCardContainer.getItemAtPosition(position);
                //TextView friends_title_label = (TextView) findViewById(R.id.friends_title_label);
                //friends_title_label.setText(itemValue);
                Log.e(":",itemValue);
            }
        });
        */
        CardModel card = adapter.getCardModel(mTopCard);

           card.setOnCardDimissedListener(new CardModel.OnCardDimissedListener() {
                @Override
                public void onLike() {
                    Log.e("-------------","-----------");
                    Log.e("-------------","-----------");
                    Log.e("-------------","-----------");
                    Log.d("Swipeable Card", "I liked it");
                    TextView instr = (TextView) findViewById(R.id.instr);
                    instr.setText("Like");
                    mTopCard -= 1;
                    Log.e("------TOP:",String.valueOf(mTopCard));
                }

                @Override
                public void onDislike() {
                    TextView instr = (TextView) findViewById(R.id.instr);
                    instr.setText("Dislike");
                    Log.d("Swipeable Card", "I did not liked it");
                    mTopCard -= 1;
                }
            });

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
