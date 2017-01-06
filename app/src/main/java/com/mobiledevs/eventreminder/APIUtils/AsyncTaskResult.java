package com.mobiledevs.eventreminder.APIUtils;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONObject;

/**
 * @author Joseph Thweatt   jathweat@asu.edu
 *
 * Interface for AsyncTask to include when the event that calls it
 * needs to be returned a result. The activity that calls the
 * AsyncTask needs to implement this interface
 *
 */

public interface AsyncTaskResult {

    // variable names for SharedPreferences
    public static final String SEARCH_QUERY = "search_query";
    public static final String JSON_RESULT_STRING = "json_result";

    Context getContext();
    void onFinish(String searchQuery, String json);
}
