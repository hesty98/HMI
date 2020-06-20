package com.linushestermeyer.hmi.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.linushestermeyer.hmi.R;
import com.linushestermeyer.hmi.network.MessageHandler;
import com.linushestermeyer.hmi.util.Constants;
import com.linushestermeyer.hmi.util.ViewClickedListener;
import com.linushestermeyer.hmi.util.adapters.PriceItemAdapter;

import java.util.ArrayList;

import EnvironmentObjects.Angebot;
import Messages.ServiceDecisionMessage;
import Messages.ServiceRegistrationMessage;
import Messages.SoftwareDecisionMessage;
import Messages.SoftwareRegistrationMessage;

public class ActivityPresentOffer extends AppCompatActivity implements ViewClickedListener {
    private ServiceRegistrationMessage serviceRegistrationMessage;
    private SoftwareRegistrationMessage softwareRegistrationMessage;

    private ArrayList<Angebot> angebote = new ArrayList<>();
    private ArrayList<String> angebotTitel = new ArrayList<>();
    private int chosenPosition =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present_offer);
        try {
            serviceRegistrationMessage = (ServiceRegistrationMessage) getIntent().getSerializableExtra(Constants.SERVICE_REGISTRATION_MESSAGE);
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.e("Tag", "Yep, i got called.");
        TextView offer_title = findViewById(R.id.offer_title);
        TextView descriptionTV = findViewById(R.id.descriptionTV);
        ListView priceList = findViewById(R.id.priceList);
        MaterialButton buy = findViewById(R.id.buyButton);
        MaterialButton dontbuy = findViewById(R.id.dontBuyButton);


        if(serviceRegistrationMessage != null) {
            offer_title.setText("Use Service: "+ serviceRegistrationMessage.getDescription().getTitle());
            descriptionTV.setText(serviceRegistrationMessage.getDescription().getDescription());
            angebote= serviceRegistrationMessage.getDescription().getAngebote();
            angebotTitel = serviceRegistrationMessage.getDescription().getAngebotTitel();

            buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MessageHandler.getInstance().sendToSam(new ServiceDecisionMessage(serviceRegistrationMessage.getInquiryID(), true, serviceRegistrationMessage.getProvider(), serviceRegistrationMessage.getRequiredSWID()));
                    finish();
                }
            });
            dontbuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MessageHandler.getInstance().sendToSam(new ServiceDecisionMessage(serviceRegistrationMessage.getInquiryID(), false, serviceRegistrationMessage.getProvider(),serviceRegistrationMessage.getRequiredSWID()));
                    finish();
                }
            });
        }else{
            try {
                softwareRegistrationMessage = (SoftwareRegistrationMessage) getIntent().getSerializableExtra(Constants.SOFTWARE_REGISTRATION_MESSAGE);
            }catch (Exception e){
                e.printStackTrace();
            }
            offer_title.setText("Install Software: "+softwareRegistrationMessage.getDescription().getTitle());
            descriptionTV.setText(softwareRegistrationMessage.getDescription().getDescription());

            angebote= softwareRegistrationMessage.getDescription().getAngebote();
            angebotTitel = softwareRegistrationMessage.getDescription().getAngebotTitel();

            buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Todo: send the chosen angebot

                    ConstraintLayout layout = findViewById(R.id.layout);
                    Snackbar snackbar = Snackbar
                            .make(layout, angebotTitel.get(chosenPosition)+":   "+angebote.get(chosenPosition), Snackbar.LENGTH_LONG);
                    snackbar.show();
                    MessageHandler.getInstance().sendToSam(new SoftwareDecisionMessage(softwareRegistrationMessage.getInquiryID(), true, softwareRegistrationMessage.getSoftwareID(),softwareRegistrationMessage.getProvider()));
                    finish();
                }
            });
            dontbuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MessageHandler.getInstance().sendToSam(new SoftwareDecisionMessage(softwareRegistrationMessage.getInquiryID(), false, softwareRegistrationMessage.getSoftwareID(),softwareRegistrationMessage.getProvider()));
                    finish();
                }
            });
        }
        if(angebote.size() ==1)
            chosenPosition=0;
        PriceItemAdapter adapter = new PriceItemAdapter(this, angebote, angebotTitel, this);
        priceList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void viewClicked(int position, String price) {
        ConstraintLayout layout = findViewById(R.id.layout);
        chosenPosition=position;
        Log.e("Tag", position+"");
        Snackbar snackbar = Snackbar
                .make(layout, "Preis: "+price, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
