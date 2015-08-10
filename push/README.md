Create the project:

https://console.developers.google.com/project

Create a project because you need the package name:
1. Open Android Studio
2. Start project
3. and so on.

Enable Google Push Services:

https://developers.google.com/mobile/add?platform=android&cntapi=gcm&cnturl=https:%2F%2Fdevelopers.google.com%2Fcloud-messaging%2Fandroid%2Fclient&cntlbl=Continue%20Adding%20GCM%20Support&%3Fconfigured%3Dtrue

Your going to be given a Server API Key and SENDER ID, write them down privately somewhere.

Click the generate config files, copy the download file to the /app/ directory.

Add
```
classpath 'com.google.gms:google-services:1.3.0-beta1'
```
to the top level build.gradle.

then go down a level and add 
```
apply plugin: 'com.google.gms.google-services'
compile "com.google.android.gms:play-services:7.5.+"
```
to the app/build.gradle.

TOP LEVEL ONE:
```
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:1.2.3'

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
        classpath 'com.google.gms:google-services:1.3.0-beta1'
    }
}

allprojects {
    repositories {
        jcenter()
    }
}
```

APP/build.gradle
```
apply plugin: 'com.android.application'
apply plugin: 'com.google.gms.google-services'

android {
    compileSdkVersion 22
    buildToolsVersion '22.0.1'

    defaultConfig {
        applicationId "YOUR_PACKAGE_NAME"
        minSdkVersion 19
        targetSdkVersion 22
        versionCode 1
        versionName "1.0"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    compile 'com.google.android.gms:play-services:7.5.+'
    compile 'com.android.support:appcompat-v7:22.2.1'
}
```

Add Permissions to Android.Manifest.
(Above the application tag)
```
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.GET_ACCOUNTS" />
<uses-permission android:name="android.permission.WAKE_LOCK" />
<uses-permission android:name="com.google.android.c2dm.permission.RECEIVE" />

<permission android:name="YOUR_PACKAGE_NAME.permission.C2D_MESSAGE"
        android:protectionLevel="signature" />
<uses-permission android:name="YOUR_PACKAGE_NAME.permission.C2D_MESSAGE" />
```

aidl missing error:

- Right click project folder > Open Module Settings > app > Build Tools 
- Change from 23.0.0.rc1 to 22.0.1

http://stackoverflow.com/questions/30506406/aidl-is-missing-android-studio



Following:
https://developers.google.com/cloud-messaging/android/client