package com.vanhack.wafer.viewpresenter.home;

import com.vanhack.wafer.viewpresenter.BaseView;

/**
 * Contract to define our View/Presenter interface
 */
public interface MainContract {

    //The Presenter will implement this
    interface Presenter {
        void callApi();
    }

    //The View will implement this
    interface View extends BaseView {
        void updateView();
    }
}
