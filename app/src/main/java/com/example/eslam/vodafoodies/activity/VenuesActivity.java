package com.example.eslam.vodafoodies.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.eslam.vodafoodies.R;
import com.example.eslam.vodafoodies.adapter.venues_adapter.VenueAdapter;
import com.example.eslam.vodafoodies.model.Venue;
import com.example.eslam.vodafoodies.network.NetworkCallback;
import com.example.eslam.vodafoodies.network.NetworkManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class VenuesActivity extends AppCompatActivity {

    RecyclerView venuesRecyclerView;
    VenueAdapter venueAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venues);
        initViews();
        getVenues();
    }

    private void getVenues() {
        NetworkManager.getInstance().listedVenues(new NetworkCallback() {
            @Override
            public void onSuccess(Object responseBody) {
                Map<String,Venue> responseMap = (Map<String, Venue>) responseBody;
                Collection<Venue> venues = responseMap.values();
                venueAdapter.setVenues(new ArrayList<>(venues));
                venueAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable error) {
                Log.i("MyTag",error.getMessage());
            }
        });
    }

    private void initViews() {
        venuesRecyclerView = (RecyclerView) findViewById(R.id.venues_recycler_view);
        venuesRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        venueAdapter = new VenueAdapter(new ArrayList<Venue>());
        venuesRecyclerView.setAdapter(venueAdapter);
    }
}
