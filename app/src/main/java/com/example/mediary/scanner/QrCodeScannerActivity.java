package com.example.mediary.scanner;

import android.app.AlertDialog;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.mediary.R;
import com.google.zxing.Result;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QrCodeScannerActivity extends AppCompatActivity
        implements ZXingScannerView.ResultHandler, View.OnClickListener {

//    private ZXingScannerView mScannerView;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_scanner);
//    }
//
//    public void onScan(View view) {
//        mScannerView = new ZXingScannerView(getApplicationContext());   // Programmatically initialize the scanner view
//        setContentView(mScannerView);                // Set the scanner view as the content view
//        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
//        mScannerView.startCamera();          // Start camera on resume
//    }
////    @Override
////    public void onResume() {
////        super.onResume();
////        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
////        mScannerView.startCamera();          // Start camera on resume
////    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        mScannerView.stopCamera();           // Stop camera on pause
//    }
//
//    @Override
//    public void handleResult(Result rawResult) {
//        Toast.makeText(getApplicationContext(),rawResult.getText(), Toast.LENGTH_SHORT).show();
//
////        ScannerActivity.tvresult.setText(rawResult.getText());
////        onBackPressed();
//
//        // If you would like to resume scanning, call this method below:
//        mScannerView.resumeCameraPreview(this);
//    }
        private ZXingScannerView mScannerView;
//        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        String customerId = "";
        static final String TAG = "Main Acvity";
        private TextToSpeech tts;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_qr_code_scanner);
        }


        @Override
        public void onClick(View v) {

        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
//            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            return super.onOptionsItemSelected(item);
        }


        //******************************************************************************************************************
        public void QrScanner(View view){
            mScannerView = new ZXingScannerView(this);      // Programmatically initialize the scanner view
            setContentView(mScannerView);
            mScannerView.setResultHandler(this);            // Register ourselves as a handler for scan results.
            mScannerView.startCamera();                     // Start camera
        }

        @Override
        public void onPause() {
            super.onPause();
            mScannerView.stopCamera();                      // Stop camera on pause
        }

        @Override
        public void handleResult(Result rawResult) {
            // Do something with the result here

            Log.e("handler", rawResult.getText());                          // Prints scan results
            Log.e("handler", rawResult.getBarcodeFormat().toString());      // Prints the scan format (qrcode)

            // show the scanner result into dialog box.
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Scan Result");
            builder.setMessage(rawResult.getText());
            AlertDialog alert1 = builder.create();
            alert1.show();

//            //INSERT DATA TO FIREBASE
//            DatabaseReference usersRef = database.child("users");
//            Map<String, FirebaseQueryQRCode> users = new HashMap<String, FirebaseQueryQRCode>();
//
//            customerId = "0001";
//            String message = rawResult.getText();
//            users.put("elderly_profile", new FirebaseQueryQRCode(customerId,message,"N","N","N","N","N","N","N","N"));
//            usersRef.setValue(users);
            finish();

        }

}