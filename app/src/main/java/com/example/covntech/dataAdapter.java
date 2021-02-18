package com.example.covntech;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class dataAdapter extends ArrayAdapter<dataFormat> {

    public dataAdapter(Activity context, ArrayList<dataFormat> words) {
        super(context, 0, words);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // check if the current view is reused else inflate the view
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_list, parent, false);
        }

        //get the object located at position
        dataFormat word_item = getItem(position);

        TextView countryName = (TextView) listItemView.findViewById(R.id.country_id);
        //gets the default Translation and set it to the text of this textView
        countryName.setText(word_item.getmCountry());

        TextView confirmed = (TextView) listItemView.findViewById(R.id.Confirmed_data);
        //gets the default Translation and set it to the text of this textView
        confirmed.setText(word_item.getmConfirmed()+" ");

        TextView recovered = (TextView) listItemView.findViewById(R.id.Recovered_data);
        //gets the default Translation and set it to the text of this textView
        recovered.setText(word_item.getmRecovered()+"");

        TextView death = (TextView) listItemView.findViewById(R.id.Death_data);
        //gets the default Translation and set it to the text of this textView
        death.setText(word_item.getmDeath()+"");
        return listItemView;
    }
}
