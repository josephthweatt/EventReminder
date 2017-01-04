package com.mobiledevs.eventreminder;

import android.support.v7.app.AppCompatActivity;

import com.mobiledevs.eventreminder.APIUtils.AsyncTaskResult;

/**
 * @author Joseph Thweatt   jathweat@asu.edu
 *
 * Display events that are relevant to the search topic entered by
 * the user in a Listview with options to view event details and
 * add the events to their saved events
 */

public class SearchResultsActivity extends AppCompatActivity  implements AsyncTaskResult {



    // from AsyncTaskResult
    @Override
    public void onFinish(String output) {

    }
}
