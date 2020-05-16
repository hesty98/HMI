package com.linushestermeyer.hmi.util.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.linushestermeyer.hmi.R;

import Messages.ServiceRegistrationMessage;

public class NotificationItemAdapter  extends ArrayAdapter<ServiceRegistrationMessage> {
    public NotificationItemAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ServiceRegistrationMessage serviceDescription = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_notification, parent, false);
        }
        // Lookup view for data population
        TextView title = (TextView) convertView.findViewById(R.id.notification_title);
        TextView notification_kind = (TextView) convertView.findViewById(R.id.notification_kind);
        title.setText(serviceDescription.getDescription().getTitle());

        if(serviceDescription.isInstallSW())
            notification_kind.setText("Softwarebedarf erkannt!");
        else
            notification_kind.setText("Service wurde registriert!");

        // Return the completed view to render on screen
        return convertView;
    }
}
