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

    ListView eventListView;
    Event event;
    View eventView;

    public void setEvent (ListView eventListView, Event event) {
        this.eventListView = eventListView;
        this.event = event;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.eventView = inflater.inflate(R.layout.results_event_fragment, container, false);

        if (event != null) {
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
                    eventView.setVisibility(View.INVISIBLE);
                    freezeListView(false);
                }
            });

            freezeListView(true);
        }

        return eventView;
    }

    /**
     * Grays out the listview and makes it inactive, so that it looks like
     * it has taken background to the event fragment
     *
     * @param freeze - true to put the ListView in the "backgorund", false
     *                  to restore the ListView
     */
    private void freezeListView (Boolean freeze) {

        int colorId = (freeze) ? R.color.white_fade : R.color.white;

        // change color of listView items
        for (int i = 0; i < eventListView.getChildCount(); i++) {

            // only set background of the visible items
            if (eventListView.getChildAt(i) != null) {
                eventListView.getChildAt(i).setBackgroundColor(
                        ContextCompat.getColor(getContext(), colorId));
            }
        }

        eventListView.setBackgroundColor(
                ContextCompat.getColor(getContext(), colorId));
        eventListView.setEnabled(!freeze);
    }

}
