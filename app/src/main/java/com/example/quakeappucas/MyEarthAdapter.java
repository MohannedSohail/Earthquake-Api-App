package com.example.quakeappucas;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyEarthAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = " of ";


    public MyEarthAdapter(@NonNull Context context, ArrayList<Earthquake> earthquakeArrayList) {
        super(context, 0, earthquakeArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.item_earthquake, parent, false);
        }

        Earthquake currentItem = getItem(position);

        TextView magnitudeTextView = listItemView.findViewById(R.id.magnitude);

        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        magnitudeTextView.setText(decimalFormat.format(currentItem.getmMagnitude()));

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        magnitudeCircle.setColor(getMagnitudeColor(currentItem.getmMagnitude()));


        String originPlace = currentItem.getmLocation();
        String locationOffset;
        String primaryLocation;

        if (originPlace.contains(LOCATION_SEPARATOR)) {
            String[] parts = originPlace.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = "Near of ";
            primaryLocation = originPlace;
        }

        TextView location_offset = listItemView.findViewById(R.id.location_offset);
        location_offset.setText(locationOffset);

        TextView primary_location = listItemView.findViewById(R.id.primary_location);
        primary_location.setText(primaryLocation);


        TextView dateTextView = listItemView.findViewById(R.id.date);
        TextView timeTextView = listItemView.findViewById(R.id.time);

        Date date = new Date(currentItem.getmTimeInMilliseconds());
        dateTextView.setText(formateDate(date));
        timeTextView.setText(formateTime(date));

        return listItemView;
    }

    private String formateDate(Date date) {
        //"Mar 3, 1984"
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("M dd, yyyy");
        return  simpleDateFormat.format(date);

    }


    private String formateTime(Date date) {
        //"4:30 PM"
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("h:mm a");
        return  simpleDateFormat.format(date);

    }


    private int getMagnitudeColor(double mag) {
        int magnitudeColorResourceId;

        int magFloor = (int) Math.floor(mag);
        switch (magFloor) {
            case 0:

            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;

            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;

            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;

            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;

            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;

            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;

            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;


            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;


            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
