package com.mwongela.gcmdemo;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Moses Mwongela
 * on 7/16/15
 * moses1889@gmail.com
 *
 * Reference: http://www.androidhive.info/2012/10/android-push-notifications-using-google-cloud-messaging-gcm-php-and-mysql/
 */

public final class CommonUtilities {
	
	// give your server registration url here
    static final String SERVER_URL = "http://weaverprojects.com/gmc/register.php";

    // Google project id
    static final String SENDER_ID = "485791388361";

    /**
     * Tag used on log messages.
     */
    static final String TAG = "Mwongela's GCM Demo";

    static final String EXTRA_MESSAGE = "message";

}
