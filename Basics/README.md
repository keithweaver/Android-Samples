# Basics


## Lifecycle

```Android
package ca.keithweaver.basicapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.i("Test", "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.i("Test", "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i("Test", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i("Test", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i("test", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i("test", "onDestroy");
    }
}
```
