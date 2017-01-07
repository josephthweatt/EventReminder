package com.mobiledevs.eventreminder.APIUtils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * @author Joseph Thweatt   jathweat@asu.edu
 *
 * A custom array adapter to add Event data to the event_list_item_layout
 */

public class EventAdapter extends ArrayAdapter<Event> {

    public EventAdapter(Context context, int eventListItemId, ArrayList<Event> eventList) {
        super(context, eventListItemId, eventList);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // TODO: build the event's list item here:

        return convertView;
    }
}
