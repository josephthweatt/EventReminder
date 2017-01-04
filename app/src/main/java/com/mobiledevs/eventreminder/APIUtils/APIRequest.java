package com.mobiledevs.eventreminder.APIUtils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.mobiledevs.eventreminder.EventReminderMain;
import com.mobiledevs.eventreminder.R;

/**
 * @author Joseph Thweatt   jathweat@asu.edu
 *
 * Sends a search query to the TicketMaster API, returns the JSON
 * result in the form of a String
 */

public class APIRequest extends AsyncTask<String, Integer, String> {

    // API values
    private static final String tmURL = "https://app.ticketmaster.com/";
    private static final String apiType = "discovery";
    private static final String version = "v2";
    private static String apiKey;

    private AsyncTaskResult resultClass;
    private String searchQuery;

    private ProgressDialog dialog;

    public APIRequest(AsyncTaskResult resultClass, String searchQuery) {
        this.resultClass = resultClass;
        this.searchQuery = searchQuery;
    }

    @Override
    protected void onPreExecute() {
        ProgressDialog dialog = new ProgressDialog(resultClass.getContext());
        dialog.setMessage("Searching Events");
        dialog.setCancelable(true);
        dialog.setInverseBackgroundForced(false); // this was deprecated as of SDK 23

        dialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        // format the search query to be used in a URL
        searchQuery.trim();


        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        dialog.hide();

        // TODO: call onFinish with the output
    }

}
