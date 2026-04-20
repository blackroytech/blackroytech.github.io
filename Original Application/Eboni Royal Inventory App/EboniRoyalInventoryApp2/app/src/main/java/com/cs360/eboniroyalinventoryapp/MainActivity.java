package com.cs360.eboniroyalinventoryapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

// must implement the on click listener interface
public class MainActivity extends Activity implements View.OnClickListener {

    EditText username;
    EditText password;
    Button loginButton;
    EditText newUsername;
    EditText newPassword;
    EditText rePassword;

    Button createLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_main);

     username =findViewById(R.id.username_edit_text);
     password = findViewById(R.id.password_edit_text1);

     loginButton = findViewById(R.id.loginButton);

     newUsername = findViewById(R.id.create_username_text_text);
     newPassword = findViewById(R.id.password_edit_text);
     rePassword = findViewById(R.id.password_edit_text2);

     createLogin = findViewById(R.id.loginButton2);

     //Create an onClickListener for login button
        //have to pass instance of activity because i have two buttons
        //use this

        loginButton.setOnClickListener(this);
        createLogin.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
     //use if-else statements to switch between buttons/ cannot use a switch case
        if (v.getId() == R.id.loginButton) {
            username.getText().toString().equals("username");
            password.getText().toString().equals("0000");

            Toast.makeText(MainActivity.this, "Successful Login", Toast.LENGTH_SHORT).show();

            Intent nextPage = new Intent(this, GridActivity.class);
            startActivity(nextPage);
            }
            else {
                    Toast.makeText(MainActivity.this, "Incorrect Login", Toast.LENGTH_SHORT).show();
                }
        if (v.getId() == R.id.loginButton2) {

            newUsername.getText().toString().equals("username");
            newPassword.getText().toString().equals("0000");
            rePassword.getText().toString().equals("0000");
            Toast.makeText(MainActivity.this,"Login Created", Toast.LENGTH_SHORT).show();
            Intent nextPage = new Intent(this, GridActivity.class);
            startActivity(nextPage);
            }
             else {
                    Toast.makeText(MainActivity.this, "Incorrect. Try Again", Toast.LENGTH_SHORT).show();

                }

            }
}

