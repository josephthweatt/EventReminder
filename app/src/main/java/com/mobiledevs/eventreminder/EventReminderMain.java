package com.mobiledevs.eventreminder;

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
    Button savedEvents;
    TextView madeBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_even_reminder_main);

        searchBar = (EditText) findViewById(R.id.search_bar);
        search = (Button) findViewById(R.id.search);
        savedEvents = (Button) findViewById(R.id.saved_events);
        madeBy = (TextView) findViewById(R.id.made_by);

        String author = "Made by " + getResources().getString(R.string.author);
        madeBy.setText(author);
    }

    // from OnClickListener
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.search:

                String searchQuery = searchBar.getText().toString();
                APIRequest request = new APIRequest(this, searchQuery);
                request.execute();
                break;
            case R.id.saved_events:

                Intent intent = new Intent(this, SavedResultsActivity.class);
                startActivity(intent);
                break;
        }
    }

    // from AsyncTaskResult
    @Override
    public void onFinish(String output) {

        // if we have a JSON string without errors, we jump to
        // SearchResultsActivity to display those results
        if (output !=  null) {
            Intent intent = new Intent(this, SearchResultsActivity.class);
            intent.putExtra(Intent.EXTRA_TEXT, output);
            startActivity(intent);
        }
    }

}
