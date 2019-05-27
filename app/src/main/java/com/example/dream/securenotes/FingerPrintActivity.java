package com.example.dream.securenotes;

import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.multidots.fingerprintauth.AuthErrorCodes;
import com.multidots.fingerprintauth.FingerPrintAuthCallback;
import com.multidots.fingerprintauth.FingerPrintAuthHelper;

public class FingerPrintActivity extends AppCompatActivity implements FingerPrintAuthCallback {

    FingerPrintAuthHelper mFingerPrintAuthHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_print);

        mFingerPrintAuthHelper = FingerPrintAuthHelper.getHelper(this,this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        //start finger print authentication
        mFingerPrintAuthHelper.startAuth();
    }
    @Override
    protected void onPause() {
        super.onPause();
        mFingerPrintAuthHelper.stopAuth();
    }

    @Override
    public void onNoFingerPrintHardwareFound() {

    }

    @Override
    public void onNoFingerPrintRegistered() {

    }

    @Override
    public void onBelowMarshmallow() {

    }

    @Override
    public void onAuthSuccess(FingerprintManager.CryptoObject cryptoObject) {
        Toast.makeText(getApplicationContext(),"Permission Granted",Toast.LENGTH_LONG).show();

        startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
    }

    @Override
    public void onAuthFailed(int errorCode, String errorMessage) {
        switch (errorCode) {    //Parse the error code for recoverable/non recoverable error.
            case AuthErrorCodes.CANNOT_RECOGNIZE_ERROR:
                Toast.makeText(getApplicationContext(),"Un Authorized User",Toast.LENGTH_LONG).show();
                //Cannot recognize the fingerprint scanned.
                break;
            case AuthErrorCodes.NON_RECOVERABLE_ERROR:
                Toast.makeText(getApplicationContext(),"Un Authorized User",Toast.LENGTH_LONG).show();

                //This is not recoverable error. Try other options for user authentication. like pin, password.
                break;
            case AuthErrorCodes.RECOVERABLE_ERROR:
             //   Toast.makeText(getApplicationContext(),"un authorized user",Toast.LENGTH_LONG).show();

                //Any recoverable error. Display message to the user.
                break;
        }
    }
}
