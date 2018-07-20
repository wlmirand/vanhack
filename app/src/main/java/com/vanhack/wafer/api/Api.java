package com.vanhack.wafer.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Class responsible for Fetch the data from the Endpoint
 * We'll implement this as a Singleton
 */
public class Api {

    private static Api sInstance;

    private static final String DATA_ENDPOINT = "https://restcountries.eu/rest/v2/all";

    private Api() {

    }

    public static Api getInstance() {
        if (sInstance == null) {
            sInstance = new Api();
        }

        return sInstance;
    }

    /**
     * Method to fetch the data from the Endpoint
     * Since this is a GET request, we just need to "download" the URL
     */
    public String getAllCountries() throws IOException {
        URL url = new URL(DATA_ENDPOINT);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

        StringBuilder jsonData = new StringBuilder();
        String tmp;
        while ((tmp = reader.readLine()) != null) {
            jsonData.append(tmp);
        }

        return jsonData.toString();
    }
}
