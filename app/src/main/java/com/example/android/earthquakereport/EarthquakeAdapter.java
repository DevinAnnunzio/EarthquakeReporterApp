package com.example.android.earthquakereport;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.util.Set;

import android.graphics.drawable.GradientDrawable;


public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = "of";

    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {

            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);

        /*Finds the textview with the id magnitude in earthquake_list_item xml*/
        TextView magnitudeView = listItemView.findViewById(R.id.magnitude);
        String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude());
        magnitudeView.setText(formattedMagnitude);


        String originalLocation = currentEarthquake.getLocation();

        String primaryLocation;
        String offsetLocation;

        /* checks to see if string contains "of"*/
        if (originalLocation.contains(LOCATION_SEPARATOR)) {
           /*splits string and puts it into an array*/
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            offsetLocation = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];

        } else {
            /*otherwise set it to "Near the" declared in string xml */
            offsetLocation = getContext().getString(R.string.near_the);
            /*primary location will be the full string*/
            primaryLocation = originalLocation;
        }

        /* Find the TextView with view ID primary location*/
        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.primary_location);
        /*set and display the view with primary location*/
        primaryLocationView.setText(primaryLocation);

        /*Find the TextView with view ID offset location*/
        TextView offsetLocationView = (TextView) listItemView.findViewById(R.id.offset_location);
        /*set and display the view with offset location*/
        offsetLocationView.setText(offsetLocation);



        Date dateObject  =  new Date(currentEarthquake.getTimeInMilliseconds());

        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        String formattedDate = formatDate(dateObject);
        dateView.setText(formattedDate);

        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        String formattedTime = formatTime(dateObject);
        timeView.setText(formattedTime);

        /* Set the proper background color on the magnitude circle.*/
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        /* Get the appropriate background color based on the current magnitude*/
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        magnitudeCircle.setColor(magnitudeColor);



        return listItemView;
    }

    /*return the formatted date string*/
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /*return the formatted date string*/
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /*return the formatted magnitude*/
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude){
        int getMagnitudeColorResourceId=0;
        int magnitudeFloor = (int) Math.floor(magnitude);

        switch (magnitudeFloor){
            case 0:
            case 1:
                getMagnitudeColorResourceId=R.color.mag1;
                break;
            case 2:
                getMagnitudeColorResourceId=R.color.mag2;
            break;
            case 3:getMagnitudeColorResourceId=R.color.mag3;
            break;
            case 4:
                getMagnitudeColorResourceId=R.color.mag4;
                break;
            case 5:
                getMagnitudeColorResourceId=R.color.mag5;
                break;
            case 6:
                getMagnitudeColorResourceId=R.color.mag6;
                break;
            case 7:
                getMagnitudeColorResourceId=R.color.mag7;
                break;
            case 8:
                getMagnitudeColorResourceId=R.color.mag8;
                break;
            case 9:
                getMagnitudeColorResourceId=R.color.mag9;
                break;
            case 10:
                getMagnitudeColorResourceId=R.color.mag10plus;
                break;

        }
        return ContextCompat.getColor(getContext(),getMagnitudeColorResourceId);

    }


}

