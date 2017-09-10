package com.example.eslam.vodafoodies.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eslam.vodafoodies.R;
import com.example.eslam.vodafoodies.model.VenueOrder;

import java.util.List;

/**
 * Created by Eslam on 8/25/2017.
 */

public class OpenOrdersAdapter extends RecyclerView.Adapter<OpenOrdersAdapter.OpenOrdersViewHolder> {

    private List<VenueOrder> openOrders;

    public OpenOrdersAdapter(List<VenueOrder> openOrders) {
        this.openOrders = openOrders;
    }

    @Override
    public OpenOrdersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OpenOrdersViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.open_order_row,parent,false));
    }

    @Override
    public void onBindViewHolder(OpenOrdersViewHolder holder, int position) {
        holder.getOwnerName().setText(openOrders.get(position).getOwner().getName());
        holder.getVenueName().setText(openOrders.get(position).getVenueTitle());
    }

    @Override
    public int getItemCount() {
        return openOrders.size();
    }

    class OpenOrdersViewHolder extends RecyclerView.ViewHolder {

        private TextView venueName, ownerName, time;
        private ImageView ownerImage;

        public OpenOrdersViewHolder(View itemView) {
            super(itemView);
            venueName = itemView.findViewById(R.id.openOrderTitle);
            ownerName = itemView.findViewById(R.id.openOrderOwner);
            ownerImage = itemView.findViewById(R.id.openOrderOwnerImageView);
            time = itemView.findViewById(R.id.openOrderTime);
        }

        public TextView getVenueName() {
            return venueName;
        }

        public TextView getOwnerName() {
            return ownerName;
        }

        public TextView getTime() {
            return time;
        }

        public ImageView getOwnerImage() {
            return ownerImage;
        }
    }
}
