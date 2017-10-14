package com.example.eslam.vodafoodies.model;

import java.util.HashMap;

/**
 * Created by Eslam on 10/14/2017.
 */

public class Item {
    String name;
    String category;
    HashMap<String, Double> prices;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public HashMap<String, Double> getPrices() {
        return prices;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", prices=" + prices +
                ", id='" + id + '\'' +
                '}';
    }
}
