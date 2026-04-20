
//ENHANCEMENT TWO: Data Structure Hashmap
package com.cs499.warehouseinventorymanagement;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
// Import items class

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {
    //make accessible for all methods
    //Initialize the map
    //key (str), value (Items)
    //key = SKU which is the unique identifier
    //value = Items
    private HashMap<String, Items> inventory = new HashMap<>();
    private List<Items> itemsList = new ArrayList<>();
    private InventoryAdapter adapter;
    //set variables


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // items layout
        setContentView(R.layout.activity_items);

        // Products will be for manufacturing warehouse for this prototype
        // Steel sheets, aluminum, plastic pellets, timber, rubber.

        //ADD products
        // .put(sku, prd, qty)
        inventory.put("001", new Items("001", "Steel Sheets", 600));
        inventory.put("002", new Items("002", "Aluminium", 750));
        inventory.put("003", new Items("003", "Plastic Pellets", 652));
        inventory.put("004", new Items("004", "Timber", 450));
        inventory.put("005", new Items("005", "Rubber", 850));

        //RecyclerView
        RecyclerView rv = findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));

        //This will convert the hashmap to a list
        itemsList.addAll(inventory.values());
        //Initialize adapter
        adapter = new InventoryAdapter(itemsList);
        //Set adapter
        rv.setAdapter(adapter);

        //Setup buttons
        Button UpdateBtn = (Button) findViewById(R.id.updateBtn);
        TextView searchSku = (EditText) findViewById(R.id.searchSku);
        Button addBtn = (Button) findViewById(R.id.addBtn);
        Button delBtn = (Button) findViewById(R.id.btnDel);
        Button logOut = (Button) findViewById(R.id.LogoutBtn);
        // Link button
        //allows me to use enter button
        searchSku.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // Triggers on 'Search' button or 'Enter' key
                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                        (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {

                    String query = searchSku.getText().toString();
                    //Returns Search
                    refreshItems(query);
                    return true;
                }
                return false;
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemDialog();
            }
        });
        UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sku = searchSku.getText().toString();
                // if sku is not empty
                if (!sku.isEmpty()) {
                    //call updateDialog
                    updateDialog(sku);
                }

            }


        });
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sku = searchSku.getText().toString();
                // if sku is not empty
                if (!sku.isEmpty()) {
                    //uses containsKey to check if sku is there
                    if (inventory.containsKey(sku)) {
                        deleteItem(sku);

                        // clear text
                        searchSku.setText("");
                        //Add Toast
                        Toast.makeText(MainActivity.this, "Product successfully deleted!", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Error deleting!", Toast.LENGTH_LONG).show();

                    }
                }
            }
        });
        //Logout button will lead back to lockscreen
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Lockscreen.class);
                startActivity(intent);
                finish();
            }
        });
    }


        // SEARCH and Refresh Method
        private void refreshItems (String query){
            //clear list
            itemsList.clear();
            // if the search is empty is null and empty
            if (query == null || query.isEmpty()) {
                //adds list back
                itemsList.addAll(inventory.values());
            }// checks if SKU matches search
            else {
                // IF search exists
                if (inventory.containsKey(query)) {
                    //Found match
                    itemsList.add(inventory.get(query));
                } else {

                    Toast.makeText(this, "No Matching Item", Toast.LENGTH_LONG).show();
                }
            }

            //notify adapter
            if (adapter != null) {


                adapter.notifyDataSetChanged();

            }
        }

//ADD Function
        public void addItemDialog(){
            //title
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Add NEW Product");

            // Create a layout for inputs
            LinearLayout layout = new LinearLayout(this);
            // vertical orientation for list
            layout.setOrientation(LinearLayout.VERTICAL);
            // edit text for views
            EditText sku = new EditText(this);
            // create hint
            sku.setHint("SKU");
            EditText prod = new EditText(this);
            prod.setHint("Product's Name");
            EditText qty = new EditText(this);
            qty.setHint("Quantity");
            //input type should be for integers
            qty.setInputType(InputType.TYPE_CLASS_NUMBER);
            //ADD LAYOUTVIEWS FOR EACH FIELD
            layout.addView(sku);
            layout.addView(prod);
            layout.addView(qty);
            //set view
            builder.setView(layout);

            //ADD button
            builder.setPositiveButton("Add", (dialog, which) -> {
                String newSku = sku.getText().toString();
                String newProd = prod.getText().toString();
                String newQty = qty.getText().toString();

                //Input validation
                if (newSku.isEmpty() || newProd.isEmpty() || newQty.isEmpty()) {
                    Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_LONG).show();
                }
                //checks if new sku already exists
                if (inventory.containsKey(newSku)) {
                    Toast.makeText(this, "Exists in System", Toast.LENGTH_LONG).show();
                    return;
                }
                //Add new product to map
                // convert qty to int
                int qtyInt = Integer.parseInt(newQty);
                inventory.put(newSku, new Items(newSku, newProd, qtyInt));
                //Refresh
                refreshItems(null);
                Toast.makeText(this, newProd + " Added to Inventory!", Toast.LENGTH_LONG).show();

            });

//ability to cancel
            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
            builder.show();

        }
        //UPDATE function : will use Dialog
        public void updateDialog (String sku){
            //containsKey checks to see if sku is inventory
            if (!inventory.containsKey(sku)) {
                Toast.makeText(this, "SKU not found!", Toast.LENGTH_LONG).show();
                return;
            }
            //retrieve (get) SKU
            Items prod = inventory.get(sku);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // Add a title
            builder.setTitle("Update Quantity: " + prod.getProduct());

            //input for user
            EditText input = new EditText(this);
            //Ensure input are numbers
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            input.setHint("Current Qty: " + prod.getQty());
            builder.setView(input);

            // BUTTONS for update
            builder.setPositiveButton("Update", (dialog, which) -> {
                String qtyInput = input.getText().toString();
                if (!qtyInput.isEmpty()) {
                    int newQty = Integer.parseInt(qtyInput);

                    //Update Product
                    prod.setQty(newQty);
                    inventory.put(sku, prod);
                    //Refresh
                    refreshItems(null);
                    Toast.makeText(this, "Updated Successfully", Toast.LENGTH_LONG).show();
                }

            });
            //ability to cancel
            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
            builder.show();


        }

        // DELETE: By SKU
        public void deleteItem (String sku){
            //if sku is removed
            if (inventory.remove(sku) != null) {

                //need to refresh
                refreshItems(null);
                //add toast message


            }
        }

    }
