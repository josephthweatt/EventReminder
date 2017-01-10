package com.mobiledevs.eventreminder.APIUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mobiledevs.eventreminder.R;

import java.util.ArrayList;

/**
 * @author Joseph Thweatt   jathweat@asu.edu
 *
 * A custom array adapter to add Event data to the event_list_item_layout.
 * To learn about how to make a custom ArrayAdapter, go to:
 *
 * https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
 */

public class EventAdapter extends ArrayAdapter<Event> {

    public EventAdapter(Context context, int eventListItemId, ArrayList<Event> eventList) {
        super(context, eventListItemId, eventList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // get the data of the event at this position
        Event event = getItem(position);

        LayoutInflater inflater = LayoutInflater.from(getContext());

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.event_list_item_layout, parent, false);

            // get Textviews from event_list_item_layout
            TextView name = (TextView) convertView.findViewById(R.id.event_list_name);
            name.setText(event.getName());

            // list items can be clicked to display details
            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: launch fragment in this activity
                    
                }
            });
        }

        return convertView;
    }
}
