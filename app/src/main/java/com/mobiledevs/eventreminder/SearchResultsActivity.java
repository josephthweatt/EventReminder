package com.mobiledevs.eventreminder;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

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

    TextView resultsHeader;
    ListView eventList;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results_activity);

         String headerString;

        // create JSONObject
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.getString(JSON_RESULT_STRING) != null) {

            String jsonString = bundle.getString(JSON_RESULT_STRING);
            createEventList(jsonString);
            headerString = "Showing results for " + bundle.getString(SEARCH_QUERY);
        } else {

            headerString = "Sorry! No results were found";
        }

        resultsHeader = (TextView) findViewById(R.id.results_header);
        resultsHeader.setText(headerString);
    }

    private void createEventList(String jsonString) {

        try {
            jsonObject = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // parse jsonObject to get the events, then put them in a object of their own

        // add array of event objects to ListView


    }


    // AsyncTaskResult methods
    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onFinish(String searchQuery, String json) {

    }

}
