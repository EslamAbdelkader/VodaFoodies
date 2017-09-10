package com.example.eslam.vodafoodies.model;

/**
 * Created by Eslam on 8/26/2017.
 */

public class UserOrder {
    String itemName;

    public UserOrder(String itemName) {

        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
