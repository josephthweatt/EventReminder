package com.mobiledevs.eventreminder.APIUtils;

import org.json.JSONArray;
import org.json.JSONException;
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
    private String url;
    private String date;
    private String startTime;
    private String venueName;
    private String priceRange; // "$min - $max"

    public Event(JSONObject jsonObject) {
        setEvent(jsonObject);
    }

    /**
     * This will set the event data according to the jsonObject
     * passed into it. This will overwrite the data already in
     * the object.
     */
    public void setEvent(JSONObject jsonObject) {

        try {
            this.name = (String) jsonObject.get("name");
            this.url = (String) jsonObject.get("url");

            // get date and startTime
            JSONObject dates = (JSONObject) jsonObject.get("dates");
            JSONObject start = (JSONObject) dates.get("start");
            this.date = start.getString("localDate");
            this.startTime = start.getString("localTime");

            // get venue name
            JSONObject _embedded = (JSONObject) jsonObject.get("_embedded");
            JSONArray venues = (JSONArray) _embedded.get("venues");
            this.venueName = ((JSONObject) venues.get(0)).getString("name");

            // get priceRange
            JSONArray priceRanges = (JSONArray) jsonObject.get("priceRanges");
            JSONObject prObject = (JSONObject) priceRanges.get(0);
            this.priceRange = "$"+ prObject.getInt("min") +" - $"+ prObject.getInt("max");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String getUrl () {
        return url;
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
