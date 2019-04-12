package com.example.gigpig;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    Fragment homeFragment;
    Fragment searchFragment;
    Fragment newFragment;
    Fragment notificationsFragment;
    Fragment profileFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        this.homeFragment = new HomeFragment();
        this.searchFragment = new SearchFragment();
        this.newFragment = new NewFragment();
        this.notificationsFragment = new NotificationsFragment();
        this.profileFragment = new ProfileFragment();


        loadFragment(this.homeFragment);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment == null)
            return false;

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();

        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                fragment = this.homeFragment;
                break;
            case R.id.navigation_search:
                fragment = this.searchFragment;
                break;
            case R.id.navigation_new:
                fragment = this.newFragment;
                break;
            case R.id.navigation_notifications:
                fragment = this.notificationsFragment;
                break;
            case R.id.navigation_profile:
                fragment = this.profileFragment;
                break;
        }

        return loadFragment(fragment);
    }
}
