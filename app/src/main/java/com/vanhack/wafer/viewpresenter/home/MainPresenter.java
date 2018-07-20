package com.vanhack.wafer.viewpresenter.home;

import android.os.AsyncTask;
import android.util.Log;

import com.vanhack.wafer.api.Api;
import com.vanhack.wafer.model.Country;
import com.vanhack.wafer.viewpresenter.BasePresenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            In this challenge, we need to show only some simple information
            so my strategy will use Regular Expression to find only the piece of information
            that is really needed (Name, Language and Currency).
         */

        //initialize our Lists to store the fields
        List<String> countries = new ArrayList<>();
        List<String> currencies = new ArrayList<>();
        List<String> languages = new ArrayList<>();

        //on JSON, we have the name attribute: { "name": "<COUNTRY_NAME>"
        //Pattern pattern = Pattern.compile("\\{(\"name\":?[^\\],)]*)|(\"currencies\":[^\\])]*)|(\"languages\":[^\\])]*)");
        Pattern pattern = Pattern.compile("(\\{\"name\":?[^,]*)");
        Matcher matcher = pattern.matcher(json);

        //while matches, filter and add to the list
        while (matcher.find()) {
            String countryName = matcher.group(0);
            //countryName = countryName.replaceAll("\\{\\s*\"name\":", "").replace("\"", "");
            //countries.add(countryName);

            /*
            String currency = matcher.group(1);
            String language = matcher.group(2);
            */

            Log.d("BRUTUS", countryName);

            //Log.d("BRUTUS", countryName + currency + language);
        }

        return null;
    }
}
