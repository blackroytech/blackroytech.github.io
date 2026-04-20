package com.cs360.eboniroyalinventoryapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class addActivity extends AppCompatActivity {

    EditText item, quantity;
    Button inc_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);

        item = findViewById(R.id.item_name);
        quantity = findViewById(R.id.item_quantity);
        inc_btn = findViewById(R.id.increase_button);

        inc_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                InventoryDatabase myDB = new InventoryDatabase(addActivity.this);
                myDB.addItem(item.getText().toString().trim(), Integer.valueOf(quantity.getText().toString().trim()));
                finish();
            }
        });
    }
}
