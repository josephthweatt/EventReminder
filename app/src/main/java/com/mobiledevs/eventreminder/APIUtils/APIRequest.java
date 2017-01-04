package com.mobiledevs.eventreminder.APIUtils;

import android.os.AsyncTask;

public class APIRequest extends AsyncTask<String, Integer, String> {

    private AsyncTaskResult resultClass;
    private String searchQuery;

    public APIRequest(AsyncTaskResult resultClass, String searchQuery) {
        this.resultClass = resultClass;
        this.searchQuery = searchQuery;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... params) {
        // format the search query to be used in a URL

        return null;
    }

    @Override
    protected void onPostExecute(String result) {

    }

}
