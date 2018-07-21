package com.vanhack.wafer.viewpresenter.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.vanhack.wafer.R;
import com.vanhack.wafer.model.Country;

import java.util.List;

/**
 * Main Activity class
 * Since we're using MVP, this Activity class is our View, so it implements the View side
 * of the contract. It also holds the reference to it's presenter to delegate all actions.
 */
public class MainActivity extends AppCompatActivity implements MainContract.View, SwipeHelper.SwipeListener {

    //Our Presenter
    private MainPresenter mPresenter;

    //UI Components
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Prepare the recycler view
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Add our animator and the divider
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setOnFlingListener(new RecyclerView.OnFlingListener() {
            @Override
            public boolean onFling(int velocityX, int velocityY) {
                Log.d("BRUTUS", "onFling");
                return false;
            }
        });
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //Swipe
        new ItemTouchHelper(new SwipeHelper(this)).attachToRecyclerView(recyclerView);

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
        //We have our result, lets fill the UI
        recyclerView.setAdapter(new CountryAdapter(countryList));
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder) {
        //Reset all other swipes
        CountryAdapter adapter = (CountryAdapter) recyclerView.getAdapter();
        for (int i=0 ; i<adapter.getItemCount() ; i++) {
            if (viewHolder.getAdapterPosition() != i) {
                adapter.notifyItemChanged(i);
            }
        }

        //Delete the holder
        //((CountryAdapter) recyclerView.getAdapter()).remove(viewHolder.getAdapterPosition());
    }
}
