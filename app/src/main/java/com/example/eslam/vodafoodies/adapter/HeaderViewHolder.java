package com.example.eslam.vodafoodies.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.eslam.vodafoodies.R;

/**
 * Created by Eslam on 8/26/2017.
 */

public class HeaderViewHolder extends RecyclerView.ViewHolder {
    TextView header;

    public HeaderViewHolder(View itemView) {
        super(itemView);
        header = itemView.findViewById(R.id.header);
    }

    public TextView getHeader() {
        return header;
    }
}
