package com.mobiledevs.eventreminder;

import com.mobiledevs.eventreminder.APIUtils.AsyncTaskResult;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

    }

    // from AsyncTaskResult
    @Override
    public void onFinish(String output) {

    }

}
