package com.example.eslam.vodafoodies.model;

/**
 * Created by Eslam on 10/14/2017.
 */

public class Venue {
    String name;
    String img;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public String getName() {

        return name;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "name='" + name + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
