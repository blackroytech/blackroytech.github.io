package com.cs360.eboniroyalinventoryapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
//class for our recylcer view layout

public class GridActivity extends AppCompatActivity{
   RecyclerView recyclerView;
   Button addButton;



   InventoryDatabase myDB;
   ArrayList<String> item_id,item_name, quantity;
   RecycleAdapter recycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.recyclerView);
        addButton = findViewById(R.id.add_btn);
        ;

        //create on click for all buttons

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addPage = new Intent(GridActivity.this, addActivity.class);
                startActivity(addPage);

                myDB = new InventoryDatabase(GridActivity.this);
                item_id = new ArrayList<>();
                item_name = new ArrayList<>();
                quantity = new ArrayList<>();


                storeData();
                recycleAdapter = new RecycleAdapter(GridActivity.this, this, item_id, item_name, quantity);
                recyclerView.setAdapter(recycleAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(GridActivity.this));


            }
        }
    }




    @Override
    protected void onActivityResult(int requestCode,int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }
    void storeData(){
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No Data. ", Toast.LENGTH_SHORT).show();
        } else{
            while (cursor.moveToNext() ){
                item_id.add(cursor.getString(0));
                item_name.add(cursor.getString(1));
                quantity.add(cursor.getString(2));


            }

        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        if (item.getItemId() == R.id.delete_all) {
            Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
        }
        return super .onOptionsItemSelected(item);
    }




    }
