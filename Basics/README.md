## Starting App

The "StartingApp" folder is the first app Android Studio provides you.



## Button Click

The "ButtonClick" folder contains an app that has an example of a button click.


One of the ways to do it:

```
Button myBtn = (Button) findViewById(R.id.myBtn);
myBtn.setOnClickListener(new View.OnClickListener() {
	@Override
	public void onClick(View v) {
		//action	
	}
});
```


### Multiple Window

The "MultipleWindow" folder contains an app that uses multiple activities and starts an activity from the MainActivity.


In the AndroidManifest.xml:
Add your new activity under the application tags,
```
<activity android:name=".SecondActivity"></activity>
```

In the MainActivity, create a new intent and start it.
```
Intent openNewWindow = new Intent(MainActivity.this, SecondActivity.class);
startActivity(openNewWindow);
```


### Passing information between Android Windows

The "MultipleWindowsWithPassingInfo" contains an app that uses multiple activities like the example above, but it also passes information and content in the intent.


Repeat steps for Multiple window. Change intent code to:
```
String value = "Star this repo";
Intent openNewWindow = new Intent(MainActivity.this, SecondActivity.class);
openNewWindow.putExtra("KEY",value);
startActivity(openNewWindow);
``` 


In your second activity, put the following to retrieve the information:
```
Intent intent = getIntent();
String value = intent.getStringExtra("KEY");
``` 