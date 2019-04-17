package com.example.gigpig;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.MarkerOptions;


public class JobViewActivity extends Activity implements OnMapReadyCallback {
    private ScrollableMapView mapView;
    private GoogleMap googleMap;
    private Button back;
    private Job job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individualjob);
        back = (Button) findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButtonOnClick();
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
    public void onMapReady(GoogleMap gMap) {
        if (gMap == null){
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
        Intent i = new Intent(getApplicationContext(), HomeFragment.class);
        startActivity(i);
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

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        this.mapView.onLowMemory();
    }
}
