package com.mobiledevs.eventreminder.APIUtils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.mobiledevs.eventreminder.EventReminderMain;
import com.mobiledevs.eventreminder.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * @author Joseph Thweatt   jathweat@asu.edu
 *
 * Sends a search query to the TicketMaster API, returns the JSON
 * result in the form of a String
 */

public class APIRequest extends AsyncTask<String, Integer, JSONObject> {

    // API values
    private static final String TM_ROOT_URL = "https://app.ticketmaster.com/discovery/v2/events.json?";
    private static String apiKey;

    private AsyncTaskResult resultClass;
    private String searchQuery;

    private ProgressDialog dialog;

    public APIRequest(AsyncTaskResult resultClass, String searchQuery) {
        this.resultClass = resultClass;
        this.searchQuery = searchQuery;
    }

    @Override
    protected void onPreExecute() {
        Context context = resultClass.getContext();
        apiKey = context.getResources().getString(R.string.api_key);

        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage("Searching Events");
        dialog.setCancelable(true);
        dialog.setInverseBackgroundForced(false); // this was deprecated as of SDK 23

        dialog.show();
    }

    /**
     * @param params
     *
     * @return jsonObject - holds search results from TicketMaster API
     */
    @Override
    protected JSONObject doInBackground(String... params) {
        String resultString = null;
        JSONObject jsonObject = null;

        String encodedSearch;
        String urlString;
        URL url;

        HttpURLConnection connection = null;
        InputStreamReader stream;
        BufferedReader buffer = null;

        try {

            // format the search query to be used in a URL
            searchQuery = searchQuery.trim();
            encodedSearch = URLEncoder.encode(searchQuery, "UTF-8");

            urlString = TM_ROOT_URL +"apikey="+ apiKey +"&keyword="+ encodedSearch;
            url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            // confirm that the connection returned a response code of 200 (OK)
            // TicketMaster API returns response code 401 when the key is invalid
            if (connection.getResponseCode() == 200) {

                // convert connection results to String
                stream = new InputStreamReader(connection.getInputStream());
                buffer = new BufferedReader(stream);

                resultString = "";
                String line;
                while ((line = buffer.readLine()) != null) {
                    resultString += line;
                }

            } else if (connection.getResponseCode() == 401) {
                dialog.setMessage("Invalid API key");
            } else {
                dialog.setMessage("Error Getting Data From Server");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (connection != null) {
                try {
                    connection.disconnect();
                } catch (Exception e) {
                    // disconnect error
                }
            }
        }


        // create JSONObject
        if (resultString != null) {
            try {
                jsonObject = new JSONObject(resultString);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return jsonObject;
    }

    @Override
    protected void onPostExecute(JSONObject result) {
        dialog.hide();

        // TODO: call onFinish with the output
    }

}
