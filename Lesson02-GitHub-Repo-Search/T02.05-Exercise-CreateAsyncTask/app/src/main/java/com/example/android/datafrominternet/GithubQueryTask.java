/*
 * Async Task : GithubQueryTask
 *
 * @author Samone Morris
 * @date   03/06/2018
 */
package com.example.android.datafrominternet;

import android.os.AsyncTask;

import com.example.android.datafrominternet.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Samone on 3/6/2018.
 */

public class GithubQueryTask extends AsyncTask<URL, Void, String> {
    /**
     * Take URL objects and perform async operations off of the Main Thread. We make the JSON
     * API request and receive the JSON response, if successful.
     *
     * @param urls  An array of URL objects to parse; we should only concentrate on the first
     *              object though
     * @return
     */
    @Override
    protected String doInBackground(URL... urls) {
        String jsonResponse = null;

        // If there are no URLs to parse or if the first URL is null, return 'null'
        if (urls.length < 1 || urls[0] == null) {
            return jsonResponse;
        }// end if

        try {
            jsonResponse = NetworkUtils.getResponseFromHttpUrl(urls[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }// end try / catch

        return jsonResponse;
    }// end doInBackground(...)

    /**
     * Display the results from doInBackground(); Update the UI on the Main Thread
     *
     * @param s     result collected after doInBackground() is complete
     */
    @Override
    protected void onPostExecute(String s) {
        //super.onPostExecute(s);

        if( s != null ){
            MainActivity.updateUi( s );
        }
    }// end onPostExecute(...)
}
