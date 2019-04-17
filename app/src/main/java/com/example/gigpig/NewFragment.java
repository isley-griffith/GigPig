package com.example.gigpig;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Fragment to manage a new fragment
 */
public class NewFragment extends Fragment implements OnMapReadyCallback, LocationListener {

    private TextView newLabel;
    private TextView success;

    private EditText jobTitleInput;
    private EditText jobDescriptionInput;
    private EditText jobPriceInput;
    private EditText jobTagsInput;

    private Button createJobButton;

    private ScrollableMapView mapView;
    private GoogleMap googleMap;

    private FirebaseAuth mAuth;

    private Job newJob;
    private LatLng startingLoc;
    private LocationManager locationManager;

    /**
     * This contains a string to deal with our map view saved state, is unique thorughout the app
     */
    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";
    private static final Float DEFAULT_ZOOM = 15f;

    /**
     * Called when the view is created, loads data from our fragment_new xml containing our gui
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_new, null);
    }

    /**
     * Called after the view is created
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.newLabel = getView().findViewById(R.id.newLabel);
        this.newLabel.setTextSize(14);
        this.newLabel.setText("New Job Listing");

        this.success = getView().findViewById(R.id.success);

        this.createJobButton = getView().findViewById(R.id.createJobButton);
        this.createJobButton.setText("Create posting");
        this.createJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createJob(view); //this view is the job that's listed in Home
            }
        });

        this.jobTitleInput = getView().findViewById(R.id.jobTitleInput);
        this.jobDescriptionInput = getView().findViewById(R.id.jobDescriptionInput);
        this.jobPriceInput = getView().findViewById(R.id.jobPriceInput);
        this.jobTagsInput = getView().findViewById(R.id.jobTagsInput);

        this.mapView = getView().findViewById(R.id.mapView);
        if (this.mapView != null) {
            this.mapView.onCreate(null);
            this.mapView.onResume();
            this.mapView.getMapAsync(this);
        }

        this.newJob = null;
    }

    /**
     * Should save our data when switching between views
     * @param outState
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        this.mapView.onSaveInstanceState(mapViewBundle);
    }

    /** the following are functions to handle the Map's default behavior **/

    @Override
    public void onResume() {
        super.onResume();
        this.mapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        this.mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        this.mapView.onStop();
    }
    @Override
    public void onPause() {
        this.mapView.onPause();
        super.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        this.mapView.onLowMemory();
    }

    /**
     * Called when map is created
     * @param gMap our instance of the map, we save this to an instance variable
     */
    @Override
    public void onMapReady(GoogleMap gMap) {
        if (gMap == null) return;
        MapsInitializer.initialize(getContext());
        googleMap = gMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        UiSettings uiSettings = this.googleMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setTiltGesturesEnabled(false);
        uiSettings.setMapToolbarEnabled(true);


        this.googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mapView.setCurrentMarker(googleMap.addMarker(
                        new MarkerOptions()
                                .position(latLng).title("This will be the location of your job")));
            }
        });

        try {
            locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this);
        } catch (SecurityException se) {
            se.printStackTrace();
        }

        LatLng cc = new LatLng(38.848450, -104.822714);

        this.startingLoc = cc;

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cc, DEFAULT_ZOOM));
        mapView.setCurrentMarker(googleMap.addMarker(new MarkerOptions().position(cc).title("This will be the location of your job")));
    }

    @Override
    public void onLocationChanged(Location location) {
        this.startingLoc = new LatLng(location.getLatitude(), location.getLongitude());
        mapView.setCurrentMarker(googleMap.addMarker(new MarkerOptions().position(this.startingLoc).title("This will be the location of your job")));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(this.startingLoc, DEFAULT_ZOOM));

        locationManager.removeUpdates(this);
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(getContext(), "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    /**
     * Helper function to parse tags from the user
     * The user can separate tags with a space or a comma and a space
     * @return the list of tags entered
     */
    private ArrayList<String> getTags() {
        String tagsString = this.jobTagsInput.getText().toString();

        // this regex parses whether or not there is a comma separating them
        return new ArrayList<>(Arrays.asList(tagsString.split(",? ")));
    }

    /**
     * Fetches data from the user input, makes a new job object and pushes it to the database
     */
    private void createJob(View view) {
        double payout = 0.0;

        if (!this.jobPriceInput.getText().toString().isEmpty())
            payout = Float.valueOf(this.jobPriceInput.getText().toString());

        String description = this.jobDescriptionInput.getText().toString();

        // job must have description
        if (description.isEmpty())
            return;

        String title = this.jobTitleInput.getText().toString();

        // job must have title
        if (title.isEmpty())
            return;

        ArrayList<String> tags = getTags();
        mAuth = FirebaseAuth.getInstance();

        String inquirerId = mAuth.getUid();
        String doerId = null;

        //Get LatLng from dropped pin
        LatLng location = mapView.getCurrentMarker().getPosition();

        // concatinating with "" is a workaround so that sort by alphabetic order works as
        // expected
        this.newJob = new Job("" + title + "", description, inquirerId, doerId, payout, tags, location);



        DatabaseHelper.writeNewJob(this.newJob);

        this.success.setText("new job '" + title + "' created");
        this.jobTitleInput.setText("");
        this.jobDescriptionInput.setText("");
        this.jobPriceInput.setText("");
        this.jobTagsInput.setText("");
        this.mapView.setCurrentMarker(googleMap.addMarker(new MarkerOptions().position(startingLoc).title("This will be the location of your job")));

    }




}


