package com.vanhack.wafer.viewpresenter.home;

import android.os.AsyncTask;

import com.vanhack.wafer.api.Api;
import com.vanhack.wafer.model.Country;
import com.vanhack.wafer.viewpresenter.BasePresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
     * This is a Network call, so we should do it off the main thread.
     * For simplicity, lets use the AsyncTask
     */
    public void callApi() {
        new NetworkTask().execute();
    }

    /**
     * AsyncTask to make our HTTP Request
     */
    class NetworkTask extends AsyncTask<Void, Void, String> {

        /**
         * Do the Backgound job
         * @param voids
         * @return
         */
        @Override
        protected String doInBackground(Void... voids) {
            try {
                return Api.getInstance().getAllCountries();
            } catch (IOException e) {
                //Something wrong
                e.printStackTrace();
                return null;
            }
        }

        /**
         * When finished, lets notify someone
         * @param result
         */
        @Override
        protected void onPostExecute(String result) {
            //We have our JSON, lets parse it
            List<Country> countryList = parseJson(result);

            //Now lets notify the UI through the View reference
            mView.updateView(countryList);
        }
    }

    /**
     * Convert our JSON to a List of Countries
     * @param json
     * @return
     */
    private List<Country> parseJson(String json) {
        /*
            On Java, we can use the org.json package that is embedded.
            Since this is already build in (not need to add dependencies at Gradle) I'll use it.
            But if I had to implement, I'd use regex to match patterns and extract only the
            Data we need to show.
        */
        try {
            List<Country> list = new ArrayList<>();

            //Our request returns an array of countries, so lets starting the Array
            JSONArray jsonArray = new JSONArray(json);

            //Lets iterate our array to get the information we need
            for (int i=0 ; i<jsonArray.length() ; i++) {
                //Name, just retrieve it
                JSONObject jsonCountry = (JSONObject)jsonArray.get(i);
                String countryName = (String) jsonCountry.get("name");

                //If exists, get only the first currency name
                JSONArray currencyArray = (JSONArray) jsonCountry.get("currencies");
                String currencyName = null;
                if (currencyArray.length() != 0) {
                    currencyName = (String) ((JSONObject) currencyArray.get(0)).get("name");
                }

                //If exists, get only the first language name
                JSONArray languagesArray = (JSONArray) jsonCountry.get("languages");
                String languageName = null;
                if (languagesArray.length() != 0) {
                    languageName = (String) ((JSONObject) languagesArray.get(0)).get("name");
                }

                //create the object and add it to the list
                list.add(new Country(countryName, currencyName, languageName));
            }

            return list;
        } catch (JSONException e) {
            //Probably we got a bad json
            e.printStackTrace();
            return null;
        }
    }
}
