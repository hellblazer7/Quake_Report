package com.example.android.quakereport;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader {
    private String mUrl;
    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    public EarthquakeLoader(Context context,String url){
        super(context);
        mUrl=url;
    }
    @Override
    public ArrayList<Earthquake> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        ArrayList<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(mUrl);
        Log.i(LOG_TAG,"Completed loadInBackground function call");
        return earthquakes;
    }
    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG,"Completed onStartLoading function call");
        forceLoad();

    }
}
