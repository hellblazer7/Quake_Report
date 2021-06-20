/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/*
 * {@link AndroidFlavorAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
 * based on a data source, which is a list of {@link AndroidFlavor} objects.
 * */
public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOG_TAG = EarthquakeAdapter.class.getSimpleName();

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        super(context, 0, earthquakes);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        // Get the {@link AndroidFlavor} object located at this position in the list
        Earthquake currentEarthquake = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude);
        magnitudeTextView.setText(formatMagnitude(currentEarthquake.getmMagnitude()));
        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getmMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        // Find the TextView in the list_item.xml layout with the ID version_number
        String originallocationstring=currentEarthquake.getmLocation();
        String locationoffset =null;
        String location =null;
        if(originallocationstring.contains("of"))
        {
                locationoffset =originallocationstring.substring(0,originallocationstring.indexOf("of")+2);
                location=originallocationstring.substring(originallocationstring.indexOf("of")+3,originallocationstring.length());

        }
        else
        {
            locationoffset="Near the";
            location=originallocationstring;
        }
        TextView offsetTextView=(TextView) listItemView.findViewById(R.id.locationoffset);
        offsetTextView.setText(locationoffset);

        TextView placeTextView = (TextView) listItemView.findViewById(R.id.place);
        placeTextView.setText(location);

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        Date date=new Date(currentEarthquake.getmDate());
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        String datetodisplay =formatDate(date);

        dateTextView.setText(datetodisplay);

        TextView timeTextView=(TextView) listItemView.findViewById(R.id.time);
        String timetodisplay=formatTime(date);
        timeTextView.setText(timetodisplay);
        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }
    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
    private String formatMagnitude(double magnitude){
        DecimalFormat formatter = new DecimalFormat("0.0");
        String magnitudestring = formatter.format(magnitude);
        return magnitudestring;
    }
    private int getMagnitudeColor(double Magnitude){
       int MagnitudeColor=0;
       int intvalueofmag=(int) Math.floor(Magnitude);
       switch(intvalueofmag){
           case 0:
           case 1:
               MagnitudeColor=ContextCompat.getColor(getContext(), R.color.magnitude1);
               break;
           case 2:
               MagnitudeColor=ContextCompat.getColor(getContext(), R.color.magnitude2);
               break;
           case 3:
               MagnitudeColor=ContextCompat.getColor(getContext(), R.color.magnitude3);
               break;
           case 4:
               MagnitudeColor=ContextCompat.getColor(getContext(), R.color.magnitude4);
               break;
           case 5:
               MagnitudeColor=ContextCompat.getColor(getContext(), R.color.magnitude5);
               break;
           case 6:
               MagnitudeColor=ContextCompat.getColor(getContext(), R.color.magnitude6);
               break;
           case 7:
               MagnitudeColor=ContextCompat.getColor(getContext(), R.color.magnitude7);
               break;
           case 8:
               MagnitudeColor=ContextCompat.getColor(getContext(), R.color.magnitude8);
               break;
           case 9:
               MagnitudeColor=ContextCompat.getColor(getContext(), R.color.magnitude9);
               break;
           case 10:
               MagnitudeColor=ContextCompat.getColor(getContext(), R.color.magnitude10plus);
               break;
           default:
               MagnitudeColor=ContextCompat.getColor(getContext(), R.color.magnitude10plus);
               break;
       }
       return MagnitudeColor;
    }


}