package com.cs499.warehouseinventorymanagement;
// This class is apart of Enhancement Two
// Class for inventory items

//Enhancement Two: Implementing HashMap Data Structure
//Enhancement Three: Implementing RoomDB entity classes

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

//add Entity - name the table
@Entity(tableName = "Inventory",
//add indices to ensure sku is unique
indices = {@Index(value = {"productSku"}, unique = true)}
)
public class Items {

    //ADD PK
    @PrimaryKey(autoGenerate = true) //auto unique id
    private int id; //ROOM db will manage id

    @ColumnInfo(name = "productSku")
    private String sku; // 8-12 abc characters

    @ColumnInfo(name = "productName")
    private String product; // name of prod
    @ColumnInfo(name = "productQty")
    private int qty; // quantity


    //Constructor

    public Items(String sku, String product, int qty) {

        this.product = product;
        this.qty = qty;
        this.sku = sku;
    }
    // Getters
    public int getId() {
        return id;
    }
    public String getSku(){
        return sku;
    }
    public String getProduct() {
        return product;
    }
    public int getQty() {
        return qty;
    }
    //Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setProduct(String product){

        this.product = product;
    }
    public void setQty(int qty) {
        this.qty= qty;
    }


}




