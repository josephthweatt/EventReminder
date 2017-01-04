package com.mobiledevs.eventreminder.APIUtils;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Joseph Thweatt   jathweat@asu.edu
 *
 * Interface for AsyncTask to include when the event that calls it
 * needs to be returned a result. The activity that calls the
 * AsyncTask needs to implement this interface
 *
 */

public interface AsyncTaskResult {

    Context getContext();
    void onFinish(String output);

}
