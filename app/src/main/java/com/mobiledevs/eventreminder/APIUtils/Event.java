package com.mobiledevs.eventreminder.APIUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Joseph Thweatt   jathweat@asu.edu
 *
 * Event holds data pertaining to the event. It is used
 * with activities to help present data. To learn about the
 * structure of TicketMasters Discovery API (the one we are
 * using), you can visit:
 *
 * http://developer.ticketmaster.com/products-and-docs/apis/discovery/v2/
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
            this.name = jsonObject.getString("name");
            this.url = jsonObject.getString("url");

            // get date and startTime
            JSONObject dates = jsonObject.getJSONObject("dates");
            JSONObject start = dates.getJSONObject("start");
            this.date = start.getString("localDate");
            this.startTime = start.getString("localTime");

            // get venue name
            JSONObject _embedded = jsonObject.getJSONObject("_embedded");
            JSONArray venues = _embedded.getJSONArray("venues");
            this.venueName = venues.getJSONObject(0).getString("name");

            // get priceRange
            JSONArray priceRanges = jsonObject.getJSONArray("priceRanges");
            JSONObject prObject = priceRanges.getJSONObject(0);
            this.priceRange = "$"+ prObject.getInt("min") +" - $"+ prObject.getInt("max");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getDate() {
        return date;
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
