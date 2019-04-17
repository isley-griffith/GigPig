package com.example.gigpig;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;


public class JobViewActivity extends Activity implements OnMapReadyCallback, ValueEventListener {
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
    private TextView jobPrice;

    private String phoneNo;
    private String message;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individualjob);
        Intent i = getIntent();
        this.job = (Job) i.getSerializableExtra("jobTappedOn");



        this.phoneNo = "";

        dateOfJob = findViewById(R.id.datePostedID);
        jobDescription = (TextView) findViewById(R.id.jobDescriptionID);
        userName = (TextView) findViewById(R.id.usernameID);
        jobPrice = findViewById(R.id.jobPriceID);

        DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference("users");
        dataRef.addValueEventListener(this);

//        this.phoneNo = DatabaseHelper.getNumber(inquirerID);


//        userName.setText(DatabaseHelper.getUsername(this.job.getInquirerId())); //will be used once user ID's are implemented
        //userName.setText("Name: " + "bungus");

        Date date = new Date(job.getCreationDate());
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yy HH:mm");
        String displayDate = "Date posted: " + formatDate.format(date);
        dateOfJob.setText(displayDate);

        String formattedDescription = "Description: " + this.job.getDescription();
        jobDescription.setText(formattedDescription);

        String formattedPrice = "Pays: " + this.job.getPayout();
        jobPrice.setText(formattedPrice);

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
                sendSMSMessage(v);
            }
        });

        this.mapView = findViewById(R.id.scrollableMapView);

        if (this.mapView != null) {
            this.mapView.onCreate(null);
            this.mapView.onResume();
            this.mapView.getMapAsync(this);
        }
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        // This method is called once with the initial value and again

        if (dataSnapshot == null) return;

        User poster = null;

        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            User user = snapshot.getValue(User.class);
            if (user.getuId() == null)
                continue;

            if (user.getuId().equals(this.job.getInquirerId()))
                poster = user;
        }

        if (poster != null) {
            this.userName.setText(poster.getUsername());

            this.phoneNo = poster.getPhoneNum();
        }
    }

    @Override
    public void onCancelled(DatabaseError error) {
        // Failed to read value
        System.out.println("Failed to read value." + error.toException());
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
        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setTiltGesturesEnabled(false);
        uiSettings.setMapToolbarEnabled(true);

        googleMap.addMarker(new MarkerOptions().position(this.job.getLocation()).title("This job is located here:"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(this.job.getLocation(), 15));

    }

    private void backButtonOnClick() {
        Intent i = new Intent(getApplicationContext(), NavigationActivity.class);
        startActivity(i);
    }

    /**
     * method invoked by OnClick() that delivers a text message to the user
     */
    protected void sendSMSMessage(View v) {
        System.out.println(this.phoneNo);
        message = "Hello I would like to do your job";
        try {
            final SmsManager smsManager = SmsManager.getDefault();

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
            alertDialog.setTitle("Send SMS");
            alertDialog.setMessage("Are you sure you want to send a text to " + this.userName.getText() + "?");
//            final String jobTitle = job.getJobTitle();
//            final String inquirerId = job.getInquirerId();

            // Specifying a listener allows you to take an action before dismissing the dialog.
            // The dialog is automatically dismissed when a dialog button is clicked.
            alertDialog.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
                    Toast.makeText(getApplicationContext(), "SMS Sent!",
                            Toast.LENGTH_LONG).show();
                }
            });

            // A null listener allows the button to dismiss the dialog and take no further action.
            alertDialog.setNegativeButton(android.R.string.no, null);
            alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
            alertDialog.show();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "SMS faild, please try again later!",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    /**
     * @param requestCode
     * @param permissions
     * @param grantResults
     * Method that asks user for permission to use SMS on the Android.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
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

