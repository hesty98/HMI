package com.linushestermeyer.hmi.view;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.linushestermeyer.hmi.R;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        Software sw1 = new Software(
                "Lane Crossing",
                "hier steht eines Tages eine ausführliche Beschreibung dieser Software, anhand welcher der Fahrer kurz und knapp erfährt, was die Software tut.",
                "29,99€"
        );
        Software sw2 = new Software(
                "Follow that taxi!",
                "With this software, you can follow a taxi. hier steht eines Tages eine ausführliche Beschreibung dieser Software, anhand welcher der Fahrer kurz und knapp erfährt, was die Software tut.",
                "50,00€"
        );
        Software sw3 = new Software(
                "Example Software",
                "hier steht eines Tages eine ausführliche Beschreibung dieser Software, anhand welcher der Fahrer kurz und knapp erfährt, was die Software tut.",
                "12,99€"
        );
        Software sw4 = new Software(
                "Basic Software",
                "hier steht eines Tages eine ausführliche Beschreibung dieser Software, anhand welcher der Fahrer kurz und knapp erfährt, was die Software tut.",
                "19,99€"
        );
        Software sw5 = new Software(
                "Speeding Software",
                "hier steht eines Tages eine ausführliche Beschreibung dieser Software, anhand welcher der Fahrer kurz und knapp erfährt, was die Software tut.",
                "109,99€"
        );
        ArrayList<Software> softwares = new ArrayList<>();
        softwares.add(sw1);
        softwares.add(sw2);
        softwares.add(sw3);
        softwares.add(sw4);
        softwares.add(sw5);
        SwShopItemAdapter adapter = new SwShopItemAdapter(getApplicationContext(), softwares);

        ListView listView = findViewById(R.id.listViewShop);
        listView.setAdapter(adapter);

    }
}
