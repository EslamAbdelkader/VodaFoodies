package com.example.eslam.vodafoodies.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.eslam.vodafoodies.R;

/**
 * Created by Eslam on 8/26/2017.
 */

public class OrderRowViewHolder extends RecyclerView.ViewHolder {
    TextView rowTitle;

    public TextView getRowTitle() {
        return rowTitle;
    }

    public OrderRowViewHolder(View itemView) {
        super(itemView);
        rowTitle = itemView.findViewById(R.id.my_order_row_title);

    }
}
