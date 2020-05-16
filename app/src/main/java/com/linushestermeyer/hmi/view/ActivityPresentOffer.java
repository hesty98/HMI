package com.linushestermeyer.hmi.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.linushestermeyer.hmi.R;
import com.linushestermeyer.hmi.network.MessageHandler;

import Messages.ServiceDecisionMessage;
import Messages.ServiceRegistrationMessage;
import Messages.SoftwareDecisionMessage;

public class ActivityPresentOffer extends AppCompatActivity {
    private ServiceRegistrationMessage registrationMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present_offer);
        try {
            registrationMessage = (ServiceRegistrationMessage) getIntent().getSerializableExtra("registration_message");
        }catch (Exception e){
            e.printStackTrace();
        }
        if(registrationMessage != null) {
            TextView offer_title = findViewById(R.id.offer_title);
            TextView descriptionTV = findViewById(R.id.descriptionTV);
            TextView pricePerUnit = findViewById(R.id.pricePerUnit);
            MaterialButton buy = findViewById(R.id.buyButton);
            MaterialButton dontbuy = findViewById(R.id.dontBuyButton);

            if(registrationMessage.isInstallSW())
                offer_title.setText("Install Software" +registrationMessage.getDescription().getTitle());
            else
                offer_title.setText("User Service"+registrationMessage.getDescription().getTitle());
            descriptionTV.setText(registrationMessage.getDescription().getDescription());
            //pricePerUnit.setText(registrationMessage.getDescription().getAngebot());

            buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(registrationMessage.isInstallSW()){
                        MessageHandler.getInstance().sendToSam(new SoftwareDecisionMessage(registrationMessage.getInquiryID(), true, registrationMessage.getServiceProvider().getRequiredSoftwareID()));
                    }else{
                        MessageHandler.getInstance().sendToSam(new ServiceDecisionMessage(registrationMessage.getInquiryID(), true, registrationMessage.getServiceProvider()));
                    }

                    finish();
                }
            });
        }

    }
}
