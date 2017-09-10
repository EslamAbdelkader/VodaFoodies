package com.example.eslam.vodafoodies.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eslam.vodafoodies.R;
import com.example.eslam.vodafoodies.adapter.OpenOrdersAdapter;
import com.example.eslam.vodafoodies.model.VenueOrder;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class OpenOrdersFragment extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;
    private List<VenueOrder> openOrders = new ArrayList<>();

    public OpenOrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_open_orders, container, false);
        recyclerView = rootView.findViewById(R.id.openOrdersListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new OpenOrdersAdapter(openOrders));
        return rootView;
    }
}
