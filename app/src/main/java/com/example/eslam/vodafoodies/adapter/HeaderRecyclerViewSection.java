package com.example.eslam.vodafoodies.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.eslam.vodafoodies.R;
import com.example.eslam.vodafoodies.model.UserOrder;

import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by Eslam on 8/26/2017.
 */

public class HeaderRecyclerViewSection extends StatelessSection {
    private String title;
    private List<UserOrder> userOrders;
    public HeaderRecyclerViewSection(String title, List<UserOrder> userOrders){
        super(R.layout.my_order_header,R.layout.my_order_row);
        this.title = title;
        this.userOrders = userOrders;
    }
    @Override
    public int getContentItemsTotal() {
        return userOrders.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new OrderRowViewHolder(view);
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        ((HeaderViewHolder)holder).getHeader().setText(title);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((OrderRowViewHolder)holder).getRowTitle().setText(userOrders.get(position).getItemName());
    }
}
