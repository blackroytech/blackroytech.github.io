//Eboni Royal

package com.cs499.warehouseinventorymanagement.Enhanment_One;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.cs499.warehouseinventorymanagement.MainActivity;
import com.cs499.warehouseinventorymanagement.R;

public class Lockscreen extends AppCompatActivity {
    // Create a cancellation signal
    /*
     *  THis is to cancel ongoing authentication
     * Create var for signal and assign it null
     *
     * */
    private CancellationSignal cancellationSignal = null;
    //create an authenticationCallback
    /*
     * tHis callback handles results of biometric attempt
     * */
    private BiometricPrompt.AuthenticationCallback authenticationCallback;

    //Add a requireAPI, Version P: the fingerprint feature can only be used on AnDroid 9 or higher
    @RequiresApi(api = Build.VERSION_CODES.P)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //login layout
        setContentView(R.layout.activity_login);
        //Set title of application
        if (getSupportActionBar()!=null){
            getSupportActionBar().setTitle("Mobile2App Inventory");
        }

        //  This will handle results iof authentication attempts
        authenticationCallback = new BiometricPrompt.AuthenticationCallback() {
            // implement error if fingerprint is not recognized or if another issue
            @Override
            public void onAuthenticationError(int errCode, CharSequence errStr) {
                super.onAuthenticationError(errCode, errStr);
                //this will notify user the error code and message
                Toast.makeText(Lockscreen.this, "Failed to Authenticate. Try Again" + errStr, Toast.LENGTH_SHORT).show();
            }

            // Implement Succeeded if finger is recognized
            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult success) {
                super.onAuthenticationSucceeded(success);
                //Toast Message will say successful
                Toast.makeText(Lockscreen.this, "Successful!", Toast.LENGTH_SHORT).show();

                //start MainPage
                Intent intent = new Intent(Lockscreen.this, MainActivity.class);
                startActivity(intent);
                finish();



            }
        };
        checkBiometricSupport();
        //Create dialog for button
        Button fingerPr = (Button) findViewById(R.id.fingerButton);
        fingerPr.setOnClickListener(new View.OnClickListener() {
            //add api requirement
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View v) {
                // Create Dialog for User to Use Touch ID
                BiometricPrompt biometricPrompt = new BiometricPrompt
                        .Builder(Lockscreen.this)
                        //Set title to title of application
                        .setTitle("Mobile2App Inventory!")
                        //Login
                        .setSubtitle("Please Login")
                        //Uses Touch ID
                        .setDescription("Uses Touch ID")
                        //If user wants to cancel login
                        .setNegativeButton("Cancel Login", getMainExecutor(), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                //add Toast Message
                                Toast.makeText(Lockscreen.this, "Login Canceled!", Toast.LENGTH_SHORT).show();
                            }
                        }).build();
                // This will start authentication callback
                biometricPrompt.authenticate(
                        // this will abort
                        getCancellationSignal(),
                        //this is for handling callback events in main
                        getMainExecutor(),
                        //handles the callback if it was successful or not
                        authenticationCallback);
            }
        });
    }
    //Cancellation Object
    private CancellationSignal getCancellationSignal(){
        cancellationSignal = new CancellationSignal();
        cancellationSignal.setOnCancelListener(
                new CancellationSignal.OnCancelListener() {
                    @Override
                    public void onCancel() {
                        //Toast message for canceled authentication
                        Toast.makeText(Lockscreen.this, "Login Canceled by user!", Toast.LENGTH_SHORT).show();

                    }
                });
        return cancellationSignal;
    }
    // This wil check if the app has fingerprint permission
    @RequiresApi(Build.VERSION_CODES.M)
    private Boolean checkBiometricSupport() {
        //keyguard manager detects if screen is locked and to verify a security  is enable
        // It also request authentication to launch certain actions
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        // if fingerprint has not been enabled in the settings
        if (!keyguardManager.isDeviceSecure()) {
            // Create Toast Message
            Toast.makeText(Lockscreen.this, "Touch ID has not been enabled in settings.", Toast.LENGTH_SHORT).show();
            return false;
        }
        //This will check if fingerprint permission has been enabled in the manifest
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_BIOMETRIC) !=
                PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(Lockscreen.this, "Touch ID Permission has not been enabled.", Toast.LENGTH_SHORT).show();
            return false;

        }
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_FINGERPRINT);
    }
}

