package com.mobiledevs.eventreminder.APIUtils;

import org.json.JSONObject;

/**
 * @author Joseph Thweatt   jathweat@asu.edu
 *
 * Event holds data pertaining to the event. It is used
 * with activities to help present data
 */

public class Event {

    // event data
    private String name;
    private String id;
    private String startTime;
    private String venueName;
    private String priceRange; // "$min = $max"

    public Event(JSONObject jsonObject) {
        setEvent(jsonObject);
    }

    /**
     * This will set the event data according to the jsonObject
     * passed into it. This will overwrite the data already in
     * the object.
     */
    public void setEvent(JSONObject jsonObject) {
        // TODO: parse jsonObject and set String variables here
    }

    public String getName() {
        return name;
    }

    public String getId () {
        return id;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getVenueName() {
        return venueName;
    }

    public String getPriceRange() {
        return priceRange;
    }
}
