package com.linushestermeyer.hmi.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.linushestermeyer.hmi.R;

import butterknife.BindView;

public class ShopFragment extends Fragment {

    @BindView(R.id.listViewShop)
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shop, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hier Angebotene SW für autos bereitstellen
        /*
        in BA: unterschied zwischen diesem App store und GooglePlayStore erläutern
         */

        //listView.setAdapter(adapter);

    }
}
