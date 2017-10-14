package com.example.eslam.vodafoodies.adapter.venues_adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eslam.vodafoodies.MyApplication;
import com.example.eslam.vodafoodies.R;
import com.example.eslam.vodafoodies.activity.MenuActivity;
import com.example.eslam.vodafoodies.model.Venue;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Eslam on 10/14/2017.
 */

public class VenueAdapter extends RecyclerView.Adapter<VenueAdapter.VenueViewHolder> {

    private List<Venue> venues;

    public VenueAdapter(List<Venue> venues) {
        this.venues = venues;
    }

    @Override
    public VenueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VenueViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.venue_row, parent, false));
    }

    @Override
    public void onBindViewHolder(VenueViewHolder holder, int position) {
        final Venue venue = venues.get(position);
        holder.nameTextView.setText(venue.getName());
        Picasso.with(MyApplication.getContext()).load(venue.getImg()).placeholder(R.drawable.venue).into(holder.imgView);
        holder.imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyApplication.getContext(), MenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("venue_id",venue.getId());
                MyApplication.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return venues.size();
    }

    public List<Venue> getVenues() {
        return venues;
    }

    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }

    class VenueViewHolder extends RecyclerView.ViewHolder {

        ImageView imgView;
        TextView nameTextView;

        public VenueViewHolder(View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.venue_image_view);
            nameTextView = itemView.findViewById(R.id.venue_name_text_view);
        }
    }
}
