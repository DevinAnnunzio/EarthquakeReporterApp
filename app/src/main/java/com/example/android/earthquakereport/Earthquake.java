package com.example.android.earthquakereport;

public class Earthquake {

    /*Magnitude of earthquake*/
    private double mMagnitude;

    /*Location of earthquake*/
    private String mLocation;

    /*Date of earthquake*/
    private long mTimeInMilliseconds;

    private String mUrl;


    /*EarthquakeActivity object that holds magnitude, location, and date*/
    public Earthquake(double magnitude, String location, long timeInMilliseconds,String url  ){

        mMagnitude = magnitude;

        mLocation = location;

        mTimeInMilliseconds = timeInMilliseconds;

        mUrl = url;

    }



    /*Because variables are private, need public getter methods so other classes can access*/
    public double getMagnitude(){
        return mMagnitude;
    }

    public String getLocation(){
        return mLocation;
    }

    public long getTimeInMilliseconds(){
        return mTimeInMilliseconds;
    }

    public String getUrl(){
        return mUrl;
    }





}

