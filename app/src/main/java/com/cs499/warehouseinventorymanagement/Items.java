package com.cs499.warehouseinventorymanagement;
// This class is apart of Enhancement Two
// Class for inventory items

//Enhancement Two: Implementing HashMap Data Structure
public class Items {
    private String product; // name of prod
    private int qty; // quantity
    private String sku; // 8-12 abc characters

    //Constructor
    public Items(String sku, String product, int qty) {

        this.product = product;
        this.qty = qty;
        this.sku = sku;
    }
    // Getters
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
    public void setProduct(String product){

        this.product = product;
    }
    public void setQty(int qty) {
        this.qty= qty;
    }


    @Override
    public String toString(){
        return "Product: " + product + "(Qty: " + qty + ")";
    }
}




