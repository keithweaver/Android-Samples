## Get user phone number

#### Add to AndroidManifest.xml:

<uses-permission android:name="android.permission.READ_PHONE_STATE"/> 

#### Add to activity:
```
Context mContext = this.getApplicationContext();
TelephonyManager tMgr = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
String mPhoneNumber = tMgr.getLine1Number();
```