package com.vanhack.wafer.viewpresenter;

import com.vanhack.wafer.api.Api;

/**
 * Base Presenter class. We ensure it has a View
 * Also, we'll define our API instance here, because different Presenters often
 * requires data from an API, so it avoids to get API reference in each presenter.
 */
public class BasePresenter<T extends BaseView> {

    //Our generic View
    protected final T mView;

    //Provides access to the APIs
    protected final Api mApi;

    public BasePresenter(T view) {
        mView = view;
        mApi = Api.getInstance();
    }
}
