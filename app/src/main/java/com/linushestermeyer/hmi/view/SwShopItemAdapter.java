package com.linushestermeyer.hmi.view;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.linushestermeyer.hmi.R;

import java.util.ArrayList;

public class SwShopItemAdapter extends ArrayAdapter<Software> {

    public SwShopItemAdapter(@NonNull Context context, ArrayList<Software> softwares) {
        super(context, 0,softwares);
    }

    public SwShopItemAdapter(@NonNull Context context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Software software = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.sw_shop_item, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.swNameTV);
        TextView tvDesc = (TextView) convertView.findViewById(R.id.swDesc);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.swPrice);
        // Populate the data into the template view using the data object
        tvName.setText(software.getName());
        tvDesc.setText(software.getDesc());
        tvPrice.setText(software.getPrice());
        // Return the completed view to render on screen
        return convertView;
    }

}
