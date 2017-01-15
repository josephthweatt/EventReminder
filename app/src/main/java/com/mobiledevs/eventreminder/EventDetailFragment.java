package com.mobiledevs.eventreminder;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.mobiledevs.eventreminder.APIUtils.Event;

import java.util.List;

/**
 * @author Joseph Thweatt   jathweat@asu.edu
 *
 * Display specific details of an Event. This Activity starts
 * from either SearchResultsActivity or SavedResultsActivity
 */

public class EventDetailFragment extends Fragment {

    private Boolean fragmentOpen = false;

    Context context;
    Event event;
    ListView eventListView;
    View eventView;

    public void setFragment(Context context, ListView eventListView, Event event) {
        this.context = context;
        this.eventListView = eventListView;
        this.event = event;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.eventView = inflater.inflate(R.layout.results_event_fragment, container, false);

        if (event != null) {
            displayFragment(true);
        }

        return eventView;
    }

    public Boolean fragmentOpen() {
        return fragmentOpen;
    }

    /**
     * method called when a user wants to return to the origional view
     */
    public void goBack() {
        displayFragment(false);
        getActivity().getSupportFragmentManager().popBackStack();
    }

    /**
     * Creates and displays the event in  a fragment. Also grays out
     * the background
     *
     * @param display - true to put the ListView in the "backgorund", false
     *                  to restore the ListView
     */
    private void displayFragment (Boolean display) {

        TextView name = (TextView) eventView.findViewById(R.id.event_fragment_name);
        TextView priceRange = (TextView) eventView.findViewById(R.id.event_fragment_price_range);
        TextView date = (TextView) eventView.findViewById(R.id.event_fragment_date);
        TextView startTime = (TextView) eventView.findViewById(R.id.event_fragment_start_time);

        name.setText(event.getName());
        priceRange.setText(event.getPriceRange());
        date.setText(event.getDate());
        startTime.setText(event.getStartTime());

        // set the image of an 'x' to exit the fragmentView
        ImageButton fragmentExit = (ImageButton) eventView.findViewById(R.id.event_fragment_exit);
        fragmentExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        int colorId = (display) ? R.color.white_fade : R.color.white;
        fragmentOpen = display;

        // change color of listView items
        for (int i = 0; i < eventListView.getChildCount(); i++) {

            // only set background of the visible items
            if (eventListView.getChildAt(i) != null) {
                eventListView.getChildAt(i).setBackgroundColor(
                        ContextCompat.getColor(context, colorId));
            }
        }

        eventListView.setBackgroundColor(
                ContextCompat.getColor(context, colorId));
        eventListView.setEnabled(!display);
    }

}
