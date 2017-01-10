package com.mobiledevs.eventreminder.APIUtils;

import android.util.Log;

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

    public static final String TAG = "APIUtils.Event";

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
     *
     * @param jsonObject - JSON data taken from Ticketmaster's API output.
     *                      Specifically, it is the JSONObject taken from
     *                      the 'events' JSONArray in the '_embedded'
     *                      JSONObject.
     */
    public void setEvent(JSONObject jsonObject) {

        try {
            this.name = jsonObject.getString("name");
        } catch (JSONException e) {
            Log.i(TAG, "name not found");
        }

        try {
            this.url = jsonObject.getString("url");
        } catch (JSONException e) {
            Log.i(TAG, "url not found");
        }

        // get date and startTime
        try {
            JSONObject dates = jsonObject.getJSONObject("dates");
            JSONObject start = dates.getJSONObject("start");
            this.date = start.getString("localDate");
            this.startTime = start.getString("localTime");
        } catch (JSONException e) {
            Log.i(TAG, "date and startTime not found");
        }

        // get venue name
        try {
            JSONObject _embedded = jsonObject.getJSONObject("_embedded");
            JSONArray venues = _embedded.getJSONArray("venues");
            this.venueName = venues.getJSONObject(0).getString("name");
        } catch (JSONException e) {
            Log.i(TAG, "venueName not found");
        }

        // get priceRange
        try {
            JSONArray priceRanges = jsonObject.getJSONArray("priceRanges");
            JSONObject prObject = priceRanges.getJSONObject(0);
            this.priceRange = "$"+ prObject.getInt("min") +" - $"+ prObject.getInt("max");
        } catch (JSONException e) {
            Log.i(TAG, "priceRange not found");
        }
    }

    public String getName() {

        if (name == null) {
            name = "";
        }

        return name;
    }

    public String getUrl() {

        if (url == null) {
            url = "";
        }

        return url;
    }

    public String getDate() {

        if (date == null) {
            date = "";
        }

        return date;
    }

    public String getStartTime() {

        if (startTime == null) {
            startTime = "";
        }

        return startTime;
    }

    public String getVenueName() {

        if (venueName == null) {
            venueName = "";
        }

        return venueName;
    }

    public String getPriceRange() {

        if (priceRange == null) {
            priceRange = "";
        }

        return priceRange;
    }
}
