package com.linushestermeyer.hmi.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.linushestermeyer.hmi.R;
import com.linushestermeyer.hmi.network.Connection;

import Messages.IMessage;
import Messages.ServiceActionMessage;
import Messages.ServiceRegistrationMessage;


public class MainActivity extends AppCompatActivity implements Connection.IMessageReader {

    private Connection connection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializes Network Communication
        //TODO: check, if a new Connection gets Passed when sending a Message from one of the activities
        connection = Connection.getInstance();
        if(!connection.isRunning()){
            connection.startConnection();
            connection.setMessageReader(this);
        }


        ImageButton home = findViewById(R.id.homeButton);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void readSocket(IMessage msg) {
        //Views wechseln oder Notification geben je nach Art der Nachricht

        if(msg instanceof ServiceRegistrationMessage){
            if(((ServiceRegistrationMessage)msg).isInstallSW()){
                //ask for install/update permission of Software
            }else{
                //present the offer of the ServiceProvider
            }
        } else if(msg instanceof ServiceActionMessage){
            //present to the driver, what the car is going to do/where it is going to go
        }
    }
}
