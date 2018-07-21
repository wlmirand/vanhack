package com.vanhack.wafer.model;

/**
 * POJO for the Country.
 * In this challenge we have to follow:
 *
 * 2. Display json data as listview with following elements parsed from json
 *    a. “name”  ->  this is Country Name
 *    b  “currencies” -> ”name" -> this is currency name, if more than 1 currency is present, first currency name is to be displayed
 *    c. “languages” -> “name”  -> this is language name, if more than 1 language is present first language is to be used
 *
 * So we can have one String for each attribute. If we are to display more currencies or languages,
 * I'd create classes for Currency and Language and add a List of the here
 */
public class Country {

    private String mName;
    private String mCurrency;
    private String mLanguage;

    public Country(String name, String currency, String language) {
        this.mName = name;
        this.mCurrency = currency;
        this.mLanguage = language;
    }

    public String getName() {
        return mName;
    }

    public String getCurrency() {
        return mCurrency;
    }

    public String getLanguage() {
        return mLanguage;
    }

    @Override
    public String toString() {
        return mName + " - " + mCurrency + " - " + mLanguage;
    }
}
