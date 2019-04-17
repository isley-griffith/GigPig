package com.example.gigpig;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Called when user is logged in
 * This activity handles our tabbed navigation
 */
public class NavigationActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    Fragment homeFragment;
    Fragment newFragment;
    Fragment profileFragment;

    /**
     * Called when view is created; will, by default, begin with the home page
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        this.homeFragment = new HomeFragment();
        this.newFragment = new NewFragment();
        this.profileFragment = new ProfileFragment();

        loadFragment(this.homeFragment);
    }

    /**
     * Called after the view is created
     */
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    /**
     * Helper function to load specific fragment into the view
     * this is either the home page, add a new job page, or the profile page
     * @param fragment fragment to load
     */
    private boolean loadFragment(Fragment fragment) {
        if (fragment == null)
            return false;

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();

        return true;
    }

    /**
     * Called when the user selects a tab item on the bottom of the screen
     * @param menuItem the menu item selected (home, new, profile)
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                fragment = this.homeFragment;
                break;
            case R.id.navigation_new:
                fragment = this.newFragment;
                break;
            case R.id.navigation_profile:
                fragment = this.profileFragment;
                break;
        }

        return loadFragment(fragment);
    }
}
