# Notes

R red and underlined, then rebuild.

Going into developer mode on an Android device.

use Alt to view drop down options to import libraries when something is red. So Alt, it will highlight the first option, arrows to navigate or enter to accept.

Need to install the SDK's and stuff.

How to record a Android screen

## Log

Log isn't `console.log` but `log.i('test','test')`

## Life Cycle

![Lifecycle](https://www.javatpoint.com/images/androidimages/Android-Activity-Lifecycle.png)


```android
public class Activity extends ApplicationContext {
    protected void onCreate(Bundle savedInstanceState);

    protected void onStart();

    protected void onRestart();

    protected void onResume();

    protected void onPause();

    protected void onStop();

    protected void onDestroy();
}
```

## Adding another Activity

- Drag and drop a button on the first activity layout
- Give the button an id
- Create a second activity in the `java/ca.ketihweaver..../` folder
- Make the second activity match the first one

```
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by weaver on 2018-01-04.
 */

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
}
```

But change the `R.layout.activity_main` to be `activity_second`.

- Create activity_second
- Drag and drop a text view on it.
- Add the activity to the android manifest

```
<activity android:name=".SecondActivity" />
```

below the other activity

Open the first activity to program the button.


```
public class MainActivity extends AppCompatActivity {

    Button openWindowBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openWindowBtn = (Button) findViewById(R.id.openWindowBtn);

        openWindowBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Intent intent = new Intent(v.getContext(), SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}
```

Use intents to start activities and use "extra" to pass data between activities. `intent.putExtra`
