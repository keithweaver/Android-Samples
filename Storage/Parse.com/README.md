## Building Android apps with Parse.com

##### September 26, 2015

This folder is examples and instructions on how to use the Parse.com as a backend. 

#### Set up

Open a new tab and go here: https://www.parse.com/docs/downloads

Download the latest Android SDK

Export the contents of the .zip SDK to /app/libs/

Delete the ParseFacebookUtilsV3-_._._ file

In Android Studio, right click the files (its under libs) and hit "Add as Library..."

Now in your /app/build.gradle file, it should reference these files in the dependencies


Go to Parse.com

Log In / Sign Up

Create an app

Open "Settings" for that app, open keys, and there is your API Keys.


#### Needed Permissions

In the Android Manifest:
```
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
```

Under the application tag:
```
android:name="YOUR_PACKAGE.Application">
```

or maybe:
```
android:name=".Application"
```



#### Connecting with the Application


Create/open /app/src/main/java/packagename/Application.java

Add:
```
Parse.initialize(this, "APPLICATION_ID", "CLIENT_KEY");
```


#### Select Query
```
ParseQuery<ParseObject> query = ParseQuery.getQuery("TABLE_NAME");
        query.whereEqualTo("COLUMN_NAME", "VALUE_SEARCHING_FOR");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + parseObjects.size() + " scores");//NUMBER OF ROWS
                    for(int i = 0;i < parseObjects.size();i++){
                        Log.d("Item:",parseObjects.get(i).get("ANOTHER_COLUMN_NAME").toString());
                    }
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
```


#### Delete Row
```
ParseQuery<ParseObject> query2 = ParseQuery.getQuery("TABLE_NAME");
        query.whereEqualTo("COLUMN_NAME","COLUMN_VALUE_BEING_LOOKED_FOR");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                if (e == null) {
                    // iterate over all messages and delete them
                    for(ParseObject row : parseObjects)
                    {
                        row.deleteEventually();
                    }
                } else {
                    Log.d("Something went wrong", e.getMessage());
                }
            }
        });
```


#### Insert new row
```
try {
            ParseObject userInfo = new ParseObject("TABLE_NAME");
            userInfo.put("COLUMN_NAME_ONE", "COLUMN_VALUE");
            userInfo.put("COLUMN_NAME_TWO", "COLUMN_VALUE_TWO");
            userInfo.saveInBackground();
        }catch(Exception e){
            Log.e("ERROR","UNABLE TO ADD");
        }
```