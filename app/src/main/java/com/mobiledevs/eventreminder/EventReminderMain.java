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
 */


public class EventReminderMain extends AppCompatActivity implements AsyncTaskResult, View.OnClickListener {

    EditText searchBar;
    Button search;
    TextView madeBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_even_reminder_main);
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
