package com.cs360.eboniroyalinventoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

/*Create a SQLite Database to hold Inventory data*/
class InventoryDatabase  extends SQLiteOpenHelper {

    private Context context;

    private static final String DATABASE_NAME = "inventory.db";
    private static final int VERSION = 1; //use version 1
    private static final String TABLE_NAME = "warehouse_inventory";
    private static final String COL_ID = "Item_id";
    private static final String COL_NAME = "Name";
    private static final String COL_QTY = "Quantity";


    public InventoryDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //create the table
        //search
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COL_NAME + " TEXT, " +
                        COL_QTY + " INTEGER);";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);

    }

    //create method to insert item to database
    void addItem(String item, int quantity) {
        //create object
        SQLiteDatabase db = this.getWritableDatabase();
        //to store data
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, item);
        cv.put(COL_QTY, quantity);
        //insert date inside table
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    //return cursor
    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);

        }
        return cursor;


    }
    void updateData(String row_id, String item, String quantity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, item);
        cv.put(COL_QTY, quantity);
        long result = db.update(TABLE_NAME, cv, "Item_id=?", new String[]{row_id});
        if(result == -1 ){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(context, "Successfuly Updaeted", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "Item_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
        }
    }
}



