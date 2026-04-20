package com.cs499.warehouseinventorymanagement.Enhancement_Three.database;
//ENHANCEMENT 3
//This will serve as the database for inventory
//Implementing Room DB
//Database

//Source: https://developer.android.com/training/data-storage/room#groovy

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.cs499.warehouseinventorymanagement.Enhancement_Two.Items;
//annotate database
@Database(entities = {Items.class}, version = 1)
//must be abstract class that extends to RoomDatabase
public abstract class InventoryDB extends RoomDatabase {
    public abstract ItemsDao itemsDao();

    //implement thread safe singleton pattern
    //SOURCE: https://www.geeksforgeeks.org/kotlin/how-to-use-singleton-pattern-for-room-database-in-android/
    //volatile prevent memory errors
    private static volatile InventoryDB INSTANCE;

    public static InventoryDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            //synchronized prevents multiple instances
            synchronized (InventoryDB.class) {
                if (INSTANCE == null) {
                    //Build database
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    InventoryDB.class, "Inventory_Database")
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}