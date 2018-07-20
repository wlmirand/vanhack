package com.vanhack.wafer.viewpresenter.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vanhack.wafer.R;

/**
 * Main Activity class
 * Since we're using MVP, this Activity class is our View, so it implements the View side
 * of the contract. It also holds the reference to it's presenter to delegate all actions.
 */
public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Cannot use something like Dagger, so we instantiate the
        // Presenter here, passing this view as a parameter
        mPresenter = new MainPresenter(this);

        //Now we can ask the Presenter to fetch the data
        mPresenter.callApi();
    }

    /**
     * Once the ApiModule finish it's Job, notify the View here
     * TODO It will need pass a parameter containing the result
     */
    @Override
    public void updateView() {

    }
}
