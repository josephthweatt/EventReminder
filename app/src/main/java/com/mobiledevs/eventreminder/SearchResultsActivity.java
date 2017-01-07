package com.mobiledevs.eventreminder;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.mobiledevs.eventreminder.APIUtils.AsyncTaskResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Joseph Thweatt   jathweat@asu.edu
 *
 * Display events that are relevant to the search topic entered by
 * the user in a Listview with options to view event details and
 * add the events to their saved events
 */

public class SearchResultsActivity extends AppCompatActivity  implements AsyncTaskResult {

    private JSONObject jsonObject;
    private ArrayList<Event> eventList;

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
        eventList = new ArrayList<Event>();

        try {
            jsonObject = new JSONObject(jsonString);

            if (jsonObject.has("_embedded")
                    && ((JSONObject) jsonObject.get("_embedded")).has("events")) {

                JSONObject embedded = (JSONObject) jsonObject.get("_embedded");
                JSONArray jsonArray = (JSONArray) embedded.get("events");

                // parse jsonArray to get the events, then put them in a object of their own
                for (int o = 0; o < jsonArray.length(); o++) {
                    JSONObject eventObject = jsonArray.get(o);
                    Event event = new Event(eventObject);
                    eventList.add(event);
                }
            }
        } catch (ClassCastException | JSONException e) {
            e.printStackTrace();
        }

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
