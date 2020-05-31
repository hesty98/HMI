package com.linushestermeyer.hmi.util.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.linushestermeyer.hmi.R;
import com.linushestermeyer.hmi.util.ViewClickedListener;

import java.util.ArrayList;

import EnvironmentObjects.Angebot;
import EnvironmentObjects.Description;

public class PriceItemAdapter extends ArrayAdapter<Angebot> {
    private ArrayList<Angebot> angebote;
    private ArrayList<String> angebotTitel;
    private ViewClickedListener listener;

    public PriceItemAdapter(@NonNull Context context, ArrayList<Angebot> angebote, ArrayList<String> angebotTitel, ViewClickedListener listener) {
        super(context, 0,angebote);
        this.angebote=angebote;
        this.angebotTitel=angebotTitel;
        this.listener=listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Angebot angebot= angebote.get(position);
        String titel = angebotTitel.get(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_angebot, parent, false);
        }


        // Preis String erstellen
        double ppu = angebot.getPricePerUnit()*100;
        Log.e("Tag", ppu*100+"");
        String price = "";
        if(ppu %100 >0){
            if(ppu%10==0) {
                price = (angebot.getPricePerUnit() + "0 €");
                double d = ppu%10;
                Log.e("Tag", d+"");
            } else {
                price = (angebot.getPricePerUnit() + " €");
                double d = ppu % 100;
                Log.e("Tag", d + "");
            }
        }
        else {
            price = (angebot.getPricePerUnit() + "0 €");
            double d = ppu;
            Log.e("Tag", d+"");
        }
        String content = price +"   "+angebotTitel.get(position);

        final TextView title = convertView.findViewById(R.id.angebotTitel);
        final TextView priceTV = convertView.findViewById(R.id.angebotPrice);

        View.OnClickListener viewListener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.viewClicked(position, content);
            }
        };
        convertView.setOnClickListener(viewListener);

        title.setOnClickListener(viewListener);
        priceTV.setOnClickListener(viewListener);

        title.setText(titel);
        priceTV.setText(price);
        return convertView;
    }
}
