package com.example.gigpig;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.Date;


public class JobViewActivity extends Activity implements OnMapReadyCallback {
    private ScrollableMapView mapView;
    private GoogleMap googleMap;
    private Button back;
    private Button openMessagingApp;
    private Job job;
    private User user;
    private TextView userName;
    private TextView jobDescription;
    private TextView priceOfJob;
    private TextView dateOfJob;
    private EditText txtphoneNo;
    private EditText txtMessage;
    private String doerID;
    private String phoneNo;
    private String message;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individualjob);
        Intent i = getIntent();
        this.job = (Job) i.getSerializableExtra("jobTappedOn");
        System.out.println(this.job);
        doerID = this.job.getDoerId();
        this.phoneNo = DatabaseHelper.getNumber(doerID);
        userName = (TextView) findViewById(R.id.usernameID);
        userName.setText("Name: " + DatabaseHelper.getUsername(job.getInquirerId())); //will be used once user ID's are implemented
        //userName.setText("Name: " + "bungus");

        Date date = new Date(job.getCreationDate());
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yy HH:mm");
        String displayDate = "Date posted: " + formatDate.format(date);
        dateOfJob = (TextView) findViewById(R.id.datePostedID);
        dateOfJob.setText(displayDate);
        String formattedDescription = "Description: " + this.job.getDescription();
        jobDescription = (TextView) findViewById(R.id.jobDescriptionID);
        jobDescription.setText(formattedDescription);


        back = (Button) findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButtonOnClick();
            }

        });
        openMessagingApp = findViewById(R.id.openMessagingAppID);
        openMessagingApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMSMessage();
            }
        });
        this.mapView = findViewById(R.id.scrollableMapView);

        if (this.mapView != null) {
            this.mapView.onCreate(null);
            this.mapView.onResume();
            this.mapView.getMapAsync(this);
        }
    }

    /**
     * @param gMap
     * Method that, if the map is ready, will set specific directions for the default appearance of the map.
     */
    @Override
    public void onMapReady(GoogleMap gMap) {
        if (gMap == null) {
            return;
        }

        MapsInitializer.initialize(getApplicationContext());
        googleMap = gMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        UiSettings uiSettings = this.googleMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);

        googleMap.addMarker(new MarkerOptions().position(this.job.getLocation()).title("This job is located here:"));
    }

    private void backButtonOnClick() {
        Intent i = new Intent(getApplicationContext(), NavigationActivity.class);
        startActivity(i);
    }

    protected void sendSMSMessage() {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS Sent!",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "SMS faild, please try again later!",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 0: {
                if (grantResults.length > MY_PERMISSIONS_REQUEST_SEND_SMS
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
    }

        /**
         * The following methods are required methods for OnMapReadyCallback and are only used once in
         * onCreate()
         */
        @Override
        public void onResume () {
            super.onResume();
            this.mapView.onResume();
        }

        @Override
        public void onStart () {
            super.onStart();
            this.mapView.onStart();
        }

        @Override
        public void onStop () {
            super.onStop();
            this.mapView.onStop();
        }
        @Override
        public void onPause () {
            this.mapView.onPause();
            super.onPause();
        }

        @Override
        public void onLowMemory () {
            super.onLowMemory();
            this.mapView.onLowMemory();
        }
    }

