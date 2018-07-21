package com.vanhack.wafer.viewpresenter.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vanhack.wafer.R;
import com.vanhack.wafer.model.Country;

import java.util.List;

/**
 * Main Activity class
 * Since we're using MVP, this Activity class is our View, so it implements the View side
 * of the contract. It also holds the reference to it's presenter to delegate all actions.
 */
public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainPresenter mPresenter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get our UI components
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Cannot use something like Dagger, so we instantiate the
        // Presenter here, passing this view as a parameter
        mPresenter = new MainPresenter(this);

        //Now we can ask the Presenter to fetch the data
        mPresenter.callApi();
    }

    /**
     * Once the Presenter finish its Job, notify the View here
     */
    @Override
    public void updateView(List<Country> countryList) {
        recyclerView.setAdapter(new CountryAdapter(countryList));
        //We have our result, lets fill the UI
    }
}
