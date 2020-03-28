package com.linushestermeyer.hmi.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.linushestermeyer.hmi.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {
    //------------------------------------------------------------------------------------//
    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;

    //------------------------------------------------------------------------------------//
    @BindView(R.id.buttonShop)
    Button mButtonShop;

    @OnClick(R.id.buttonShop) void shopIntent(){
        Fragment selectedFragment = new ShopFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                selectedFragment).commit();
    }

    //------------------------------------------------------------------------------------//
    @BindView(R.id.buttonNotifications)
    Button mButtonNotifications;

    @OnClick(R.id.buttonNotifications) void notificationsIntent(){
        Fragment selectedFragment = new MessageFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                selectedFragment).commit();
    }

    //------------------------------------------------------------------------------------//
    @BindView(R.id.buttonSettings)
    Button mButtonSettings;

    @OnClick(R.id.buttonSettings) void settingsIntent(){
        Fragment selectedFragment = new SettingsFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                selectedFragment).commit();
    }

    //------------------------------------------------------------------------------------//
    @BindView(R.id.buttonSWManagement)
    Button mButtonSWManagement;

    @OnClick(R.id.buttonSWManagement) void swManagementIntent(){
        Fragment selectedFragment = new SwManagementFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                selectedFragment).commit();
    }

    //-------------------------------End Vairable Declaration-----------------------------//


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // View Init
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        //View Content

    }
}
