package com.mobiledevs.eventreminder;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mobiledevs.eventreminder.APIUtils.AsyncTaskResult;
import com.mobiledevs.eventreminder.APIUtils.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author Joseph Thweatt   jathweat@asu.edu
 *
 * Display events that are relevant to the search topic entered by
 * the user in a Listview with options to view event details and
 * add the events to their saved events
 */

public class SearchResultsActivity extends AppCompatActivity  implements AsyncTaskResult {

    private ArrayList<Event> eventList;

    TextView resultsHeader;
    ListView eventListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results_activity);

        String headerString = null;

        // create JSONObject
        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.getString(JSON_RESULT_STRING) != null) {

            String jsonString = bundle.getString(JSON_RESULT_STRING);
            createEventList(jsonString);

            if (eventList.size() > 0) {
                headerString = "Showing results for " + bundle.getString(SEARCH_QUERY);
            }
        }

        if (headerString == null) {
            headerString = "Sorry! No results were found";
        }
        resultsHeader = (TextView) findViewById(R.id.results_header);
        resultsHeader.setText(headerString);
    }

    private void createEventList(String jsonString) {
        eventList = new ArrayList<Event>();

        try {
            JSONObject jsonObject = new JSONObject(jsonString);

            if (jsonObject.has("_embedded")
                    && jsonObject.getJSONObject("_embedded").has("events")) {

                JSONObject embedded = jsonObject.getJSONObject("_embedded");
                JSONArray jsonArray = embedded.getJSONArray("events");

                // parse jsonArray to get the events, then put them in a object of their own
                for (int o = 0; o < jsonArray.length(); o++) {
                    JSONObject eventObject = jsonArray.getJSONObject(o);
                    Event event = new Event(eventObject);
                    eventList.add(event);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // add array of event objects to ListView. You can learn more about
        // how to make a ListView at:
        // http://windrealm.org/tutorials/android/android-listview.php
        if (eventList.size() > 0) {
            ArrayAdapter<Event> adapter
                    = new ArrayAdapter<Event>(this, R.layout.event_list_item_layout, eventList);

            eventListView = (ListView) findViewById(R.id.event_list_view);
            eventListView.setAdapter(adapter);
        }
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
