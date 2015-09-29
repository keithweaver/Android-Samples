package com.weaverprojects.stripe;

/**
 * Created by Keith on 2015-09-28.
 */
public interface PaymentForm {
    public String getCardNumber();
    public String getCvc();
    public Integer getExpMonth();
    public Integer getExpYear();
}