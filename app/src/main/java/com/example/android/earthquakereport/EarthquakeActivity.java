package com.example.android.earthquakereport;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);

        /* Create a fake list of earthquake locations.*/
        ArrayList<Earthquake> earthquakes = Query.extractEarthquakes();


        /* Find a reference to the link ListView in the layout*/
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        final EarthquakeAdapter adapter = new EarthquakeAdapter(this,earthquakes);


        /* Set the adapter on the links ListView*/
        /* so the list can be populated in the user interface*/
        earthquakeListView.setAdapter(adapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Find the current earthquake*/
                Earthquake currentEarthquake = adapter.getItem(position);

                /*convert string url to URl object*/
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                /*Create a new intent to view the earthquake URI*/
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                /* Send the intent to launch a new activity*/
                startActivity(websiteIntent);
            }
        });
    }
}
