package com.example.eslam.vodafoodies.model;

/**
 * Created by Eslam on 8/25/2017.
 */

public class VenueOrder {
    String venueTitle;
    User owner;
    String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public VenueOrder(String venueTitle, User owner) {

        this.venueTitle = venueTitle;
        this.owner = owner;
    }

    public VenueOrder() {

    }

    public String getVenueTitle() {
        return venueTitle;
    }

    public void setVenueTitle(String venueTitle) {
        this.venueTitle = venueTitle;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
