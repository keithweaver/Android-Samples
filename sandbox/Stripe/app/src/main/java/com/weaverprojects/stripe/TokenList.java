package com.weaverprojects.stripe;


import com.stripe.android.model.Token;

/**
 * Created by Keith on 2015-09-28.
 */
public interface TokenList {
    public void addToList(Token token);
}
