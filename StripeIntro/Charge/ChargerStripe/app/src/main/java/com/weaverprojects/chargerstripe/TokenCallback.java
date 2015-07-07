package com.weaverprojects.chargerstripe;

import com.stripe.model.Token;

/**
 * Created by Keith on 2015-07-03.
 */

public abstract class TokenCallback {
    public abstract void onError(Exception error);
    public abstract void onSuccess(Token token);
}
