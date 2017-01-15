package com.mobiledevs.eventreminder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mobiledevs.eventreminder.APIUtils.Event;

/**
 * @author Joseph Thweatt   jathweat@asu.edu
 *
 * Display specific details of an Event. This Activity starts
 * from either SearchResultsActivity or SavedResultsActivity
 */

public class EventDetailFragment extends Fragment {

    View eventView;
    Event event;

    public void setEvent (Event event) {
        if (event == null) {
            return;
        }

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
        }

        return eventView;
    }

}
