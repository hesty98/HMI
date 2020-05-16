package com.linushestermeyer.hmi.util.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.linushestermeyer.hmi.R;

import java.util.ArrayList;

import EnvironmentObjects.Description;


//TODO: change between ServiceDescription and SoftwareDescription
public class SwShopItemAdapter extends ArrayAdapter<Description> {

    public SwShopItemAdapter(@NonNull Context context, ArrayList<Description> softwares) {
        super(context, 0,softwares);
    }

    public SwShopItemAdapter(@NonNull Context context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Description serviceDescription = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_sw_shop, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.swNameTV);
        TextView tvDesc = (TextView) convertView.findViewById(R.id.swDesc);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.swPrice);
        // Populate the data into the template view using the data object
        tvName.setText(serviceDescription.getTitle());
        tvDesc.setText(serviceDescription.getDescription());
        tvPrice.setText((serviceDescription.getAngebot().getPricePerUnit())/100+","+(serviceDescription.getAngebot().getPricePerUnit())%100);
        // Return the completed view to render on screen
        return convertView;
    }

}
