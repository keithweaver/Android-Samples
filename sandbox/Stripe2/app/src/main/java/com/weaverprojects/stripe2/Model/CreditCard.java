package com.weaverprojects.stripe2.Model;

/**
 * Created by Keith on 2015-09-28.
 */
public class CreditCard {
    private String number;
    private int month;
    private int year;
    private String cvc;

    public CreditCard(String number, int month, int year, String cvc) {
        this.number = number;
        this.month = month;
        this.year = year;
        this.cvc = cvc;
    }

    public String getNumber() {
        return number;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public String getCvc() {
        return cvc;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }
}
