package com.example.gigpig;

import android.content.pm.PackageManager;
import android.location.Location;
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

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Fragment to manage a new fragment
 */
public class NewFragment extends Fragment implements OnMapReadyCallback {

    private TextView newLabel;
    private TextView success;

    private EditText jobTitleInput;
    private EditText jobDescriptionInput;
    private EditText jobPriceInput;
    private EditText jobTagsInput;

    private Button createJobButton;

    private MapView mapView;
    private GoogleMap googleMap;

    private Job newJob;

    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_new, null);
    }

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
                createJob(view);
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
//    @Override
//    public void onDestroy() {
//        this.mapView.onDestroy();
//        super.onDestroy();
//    }
//
//    @Override
//    public void onDestroyView() {
//        this.mapView.onDestroy();
//        super.onDestroyView();
//    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        this.mapView.onLowMemory();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (googleMap == null) return;

        MapsInitializer.initialize(getContext());

        this.googleMap = googleMap;
        this.googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        UiSettings uiSettings = this.googleMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
//        this.googleMap.setMinZoomPreference(12);

        LatLng cc = new LatLng(38.848450, -104.822714);
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cc, 15));
    }

    private ArrayList<String> getTags() {
        String tagsString = this.jobTagsInput.getText().toString();

        return new ArrayList<>(Arrays.asList(tagsString.split(" ")));
    }

    // TODO: Some database stuff
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

        // placeholder
        User placeHolderUser = new User();

        this.newJob = new Job("" + title + "", description, payout, placeHolderUser, tags, null);
//        this.newJob = new Job(" a new one", "hard coded descr", 30, placeHolderUser, tags, null);



        DatabaseHelper.writeNewJob(this.newJob);
    }
}
