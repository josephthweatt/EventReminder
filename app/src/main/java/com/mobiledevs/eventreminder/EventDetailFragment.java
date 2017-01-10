package com.mobiledevs.eventreminder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobiledevs.eventreminder.APIUtils.Event;

/**
 * @author Joseph Thweatt   jathweat@asu.edu
 *
 * Display specific details of an Event. This Activity starts
 * from either SearchResultsActivity or SavedResultsActivity
 */

public class EventDetailFragment extends Fragment {

    Event event;

    public void setEvent (Event event) {
        this.event = event;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (event == null) {
            return null;
        }

        View eventView = inflater.inflate(R.layout.results_event_fragment, container, false);

        TextView name = (TextView) eventView.findViewById(R.id.event_list_name);
        TextView priceRange = (TextView) eventView.findViewById(R.id.event_list_price_range);
        TextView date = (TextView) eventView.findViewById(R.id.event_list_date);
        TextView startTime = (TextView) eventView.findViewById(R.id.event_list_start_time);

        name.setText(event.getName());
        priceRange.setText(event.getPriceRange());
        date.setText(event.getDate());
        startTime.setText(event.getStartTime());

        return eventView;
    }

}
