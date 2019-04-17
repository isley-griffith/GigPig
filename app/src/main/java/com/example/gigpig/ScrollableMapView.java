package com.example.gigpig;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;

import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.Marker;

/**
 * A MapView that better handles scrolling when contained within a ScrollView
 * Contains default functions so it behaves like a normal map view
 */
public class ScrollableMapView extends MapView {

    private ViewParent mViewParent;

    /**
     * We only want to have one marker on our map at a time
     */
    private Marker currentMarker;

    public Marker getCurrentMarker() {
        return currentMarker;
    }

    /**
     * Will delete the marker present and replace it with a new one
     * @param currentMarker the marker to be displayed
     */
    public void setCurrentMarker(Marker currentMarker) {
        if (this.currentMarker != null)
            this.currentMarker.remove();

        this.currentMarker = currentMarker;
    }

    public ScrollableMapView(Context context) {
        super(context);
    }

    public ScrollableMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollableMapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setViewParent(@Nullable final ViewParent viewParent) { //any ViewGroup
        mViewParent = viewParent;
    }

    public ScrollableMapView(Context context, GoogleMapOptions options) {
        super(context, options);
    }


    /**
     * Here we override when the user scrolls on the map so that the parent ScrollView does
     * not take priority, we only want to interact with the map
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // Request all parents to relinquish the touch events

        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }
}
