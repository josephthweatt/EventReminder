package com.mobiledevs.eventreminder;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.mobiledevs.eventreminder.APIUtils.AsyncTaskResult;
import com.mobiledevs.eventreminder.APIUtils.Event;
import com.mobiledevs.eventreminder.APIUtils.EventAdapter;

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

public class SearchResultsActivity extends FragmentActivity implements AsyncTaskResult, View.OnClickListener{

    private ArrayList<Event> eventList;

    EventDetailFragment eventFragment;
    View fragmentView;
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
                headerString = "Showing results for \""
                        + bundle.getString(SEARCH_QUERY) +"\"";
            }
        }

        if (headerString == null) {
            headerString = "Sorry! No results were found";
        }
        resultsHeader = (TextView) findViewById(R.id.results_header);
        resultsHeader.setText(headerString);

    }

    @Override
    public void onClick(View v) {

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

            EventAdapter adapter
                    = new EventAdapter(this, R.layout.event_list_item_layout, eventList);

            eventListView = (ListView) findViewById(R.id.event_list_view);
            eventListView.setAdapter(adapter);

            eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                // this is the code that will be executed when a list item is clicked
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    eventFragment = new EventDetailFragment();
                    eventFragment.setFragment(getContext(), eventListView, eventList.get(position));
                    transaction.replace(R.id.results_event_fragment, eventFragment);

                    // the user will be able to navigate back to the listview using the back button
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        if (eventFragment.fragmentOpen()) {
            eventFragment.goBack();
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
