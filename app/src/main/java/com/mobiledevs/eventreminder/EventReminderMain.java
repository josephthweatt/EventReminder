package com.mobiledevs.eventreminder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mobiledevs.eventreminder.APIUtils.*;

/**
 * @author Joseph Thweatt   jathweat@asu.edu
 *
 * Provides the user with a way to search new events
 * and look at their saved events
 */

public class EventReminderMain extends AppCompatActivity implements AsyncTaskResult, View.OnClickListener {

    EditText searchBar;
    Button search;
    TextView madeBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_reminder_main);

        searchBar = (EditText) findViewById(R.id.search_bar);
        search = (Button) findViewById(R.id.search);
        search.setOnClickListener(this);
        madeBy = (TextView) findViewById(R.id.made_by);

        String author = "Made by " + getResources().getString(R.string.author);
        madeBy.setText(author);
    }

    /**
     * from OnClickListener, this will get called whenever a view is touched.
     * However, it will only do something 'if' that view is the 'search' view
     */
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.search) {
            String searchQuery = searchBar.getText().toString();

            if (!searchQuery.equals("")) {
                APIRequest request = new APIRequest(this, searchQuery);
                request.execute();
            }
        }
    }

    // AsyncTaskResult methods
    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onFinish(String searchQuery, String json) {

        // if we have a JSON string without errors, we jump to
        // SearchResultsActivity to display those results
        if (json !=  null) {

            Intent intent = new Intent(this, SearchResultsActivity.class);
            intent.putExtra(SEARCH_QUERY, searchQuery);
            intent.putExtra(JSON_RESULT_STRING, json);
            startActivity(intent);
        }
    }

}
