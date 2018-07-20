package com.vanhack.wafer.viewpresenter;

/**
 * Base Presenter class. We ensure it has a View
 * Also, we'll define our API instance here, because different Presenters often
 * requires data from an API, so it avoids to get API reference in each presenter.
 */
public class BasePresenter<T extends BaseView> {

    //Our generic View
    protected final T mView;

    //Provides access to the APIs
    //protected final SomeApiClass mApi;

    public BasePresenter(T view) {
        mView = view;
        //mApi = Api reference.... normally a singleton. It also can be Injected
    }
}
