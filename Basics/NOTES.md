# Notes

R red and underlined, then rebuild.

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
