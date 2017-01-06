package com.mobiledevs.eventreminder;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;

import com.mobiledevs.eventreminder.APIUtils.AsyncTaskResult;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Joseph Thweatt   jathweat@asu.edu
 *
 * Display events that are relevant to the search topic entered by
 * the user in a Listview with options to view event details and
 * add the events to their saved events
 */

public class SearchResultsActivity extends AppCompatActivity  implements AsyncTaskResult {

    private JSONObject jsonObject;

    @Override
    public void onCreate(Bundle savedInstanceState) {


        // create JSONObject
        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.getString(JSON_RESULT_STRING) != null) {

            String resultString = bundle.getString(JSON_RESULT_STRING);
            try {
                jsonObject = new JSONObject(resultString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    // AsyncTaskResult methods
    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onFinish(String json) {

    }

}
