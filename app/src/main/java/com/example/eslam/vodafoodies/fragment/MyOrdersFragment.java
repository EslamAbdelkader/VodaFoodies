package com.example.eslam.vodafoodies.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eslam.vodafoodies.R;
import com.example.eslam.vodafoodies.adapter.HeaderRecyclerViewSection;
import com.example.eslam.vodafoodies.model.UserOrder;

import java.util.Arrays;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrdersFragment extends Fragment {

    private RecyclerView recyclerView;
    private View rootView;
    private SectionedRecyclerViewAdapter adapter;
    public MyOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_my_orders, container, false);
        recyclerView = rootView.findViewById(R.id.my_orders_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new SectionedRecyclerViewAdapter();
        adapter.addSection(new HeaderRecyclerViewSection("Eltab3y", Arrays.asList(new UserOrder("fool"),new UserOrder("ta3mya"))));
        adapter.addSection(new HeaderRecyclerViewSection("Mesh Eltab3y", Arrays.asList(new UserOrder("fool bardo"),new UserOrder("ta3mya bardo"))));
        recyclerView.setAdapter(adapter);
        return rootView;
    }

}
