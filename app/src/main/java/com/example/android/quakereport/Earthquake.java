package com.example.android.quakereport;

public class Earthquake {
    private double mMagnitude;
    private String mLocation;
    private long mDate;
    private String mURL;

    public Earthquake(double Magnitude, String Location, long Date, String URL) {
        mMagnitude = Magnitude;
        mLocation = Location;
        mDate = Date;
        mURL = URL;
    }

    public double getmMagnitude() {
        return mMagnitude;
    }

    public String getmLocation() {
        return mLocation;
    }

    public long getmDate() {
        return mDate;
    }

    public String getmURL() {
        return mURL;
    }
}
