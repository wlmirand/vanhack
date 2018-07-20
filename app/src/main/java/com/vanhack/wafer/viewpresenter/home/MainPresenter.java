package com.vanhack.wafer.viewpresenter.home;

import com.vanhack.wafer.viewpresenter.BasePresenter;

/**
 * Presenter to handle MainActivity's business logic
 */
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private static final String DATA_ENDPOINT = "https://restcountries.eu/rest/v2/all";

    public MainPresenter(MainContract.View view) {
        super(view);
    }

    /**
     * Just ask the "API Module" to fetch the Data!!
     */
    public void callApi() {

    }
}
