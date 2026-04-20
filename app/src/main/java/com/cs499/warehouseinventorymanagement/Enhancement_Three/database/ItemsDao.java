package com.cs499.warehouseinventorymanagement.Enhancement_Three.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.cs499.warehouseinventorymanagement.Enhancement_Two.Items;

import java.util.List;

//ROOM - DAO Data Access Object
@Dao
public interface ItemsDao {

    //INSERT method
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertItems(Items items);

    // UPDATE
    @Update
    public void updateItems(Items items);

    //DELETE
    @Delete
    public void deleteItems(Items items);

    //SEARCH
    //Select form table name
    @Query("SELECT * FROM Inventory")
    //gets inventory
    List<Items> getAllItems();

    //Search by SKU
    @Query("SELECT * FROM Inventory WHERE productSku = :sku")
    public Items findBySku(String sku);

}

