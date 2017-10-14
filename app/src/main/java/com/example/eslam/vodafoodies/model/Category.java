package com.example.eslam.vodafoodies.model;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Eslam on 10/14/2017.
 */

public class Category {
    HashMap<String, Item> items;
    String name;
    List<String> sizes;

    public HashMap<String, Item> getItems() {
        return items;
    }

    public String getName() {
        return name;
    }

    public List<String> getSizes() {
        return sizes;
    }

    @Override
    public String toString() {
        return "Category{" +
                "items=" + items +
                ", name='" + name + '\'' +
                ", sizes=" + sizes +
                '}';
    }
}
