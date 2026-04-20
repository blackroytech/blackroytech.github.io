package com.cs360.eboniroyalinventoryapp;

import android.app.AlertDialog;
import android.app.Notification;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class updateActvity extends AppCompatActivity {
    EditText item_input, quantity_input;
    Button updatebtn, deletebtn;
    String id, name, quantity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_actvity);

        item_input = findViewById(R.id.item_name2);
        quantity_input = findViewById(R.id.item_quantity2);
        updatebtn = findViewById(R.id.update_button);
        deletebtn = findViewById(R.id.delete_button);
        getIntentData();

        //Title bar
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InventoryDatabase myDB = new InventoryDatabase(updateActvity.this);
                name = item_input.getText().toString().trim();
                quantity = quantity_input.getText().toString().trim();

                myDB.updateData(id, name, quantity);

            }
        });
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmData();


            }
        });



    }
    void getIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                    getIntent().hasExtra("quantity")) {
            //Getting data from intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            quantity = getIntent().getStringExtra("quantity");
            item_input.setText(name);
            quantity_input.setText(quantity);
        } else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();

        }
    }
    void confirmData(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                InventoryDatabase myDB = new InventoryDatabase(updateActvity.this);
                myDB.deleteOneRow(id);
                finish();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });
        builder.create().show();
    }
}