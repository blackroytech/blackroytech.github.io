package com.cs360.eboniroyalinventoryapp;

import android.widget.Button;

public class Items {
    String itemId;
    String itemName;
    int quantity;


    public Items(String itemName, int quantity) { //constructor
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }
}
