package com.example.gigpig;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import java.util.*;
import android.location.Location;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class SortByLocationStrategyTest {

    @Test
    public void testSortLocation() {
        ArrayList<Job> jobs = new ArrayList<Job>();
        ArrayList<String> tags = new ArrayList<String>();
        User user = new User();

        Location userLoc = new Location("user location");
        userLoc.setLongitude(-104.823774);
        userLoc.setLatitude(38.849078);

        double bemisLat = 38.848312;
        double bemisLon = -104.826460;
        Location bemisLoc = new Location("bemis location");
        bemisLoc.setLongitude(bemisLon);
        bemisLoc.setLatitude(bemisLat);


        double slocumLat = 38.847136;
        double slocumLon = -104.821932;
        Location slocumLoc = new Location("slocum location");
        slocumLoc.setLatitude(slocumLat);
        slocumLoc.setLongitude(slocumLon);


        double olymLat = 38.839430;
        double olymLon = -104.796087;
        Location olymLoc = new Location("olympic location");
        olymLoc.setLatitude(olymLat);
        olymLoc.setLongitude(olymLon);

        Job job = new Job("YOOOOO WASH MY stuff", "needa wash buddy", 3.20, user, tags, bemisLoc);
        Job job1 = new Job("AAAAYOOOOO WASH MY stuff", "needa wash buddy", 3.20, user, tags, slocumLoc);
        Job job2 = new Job("BAAYOOOOO WASH MY stuff", "needa wash buddy", 3.20, user, tags, olymLoc);

        jobs.add(job);
        jobs.add(job1);
        jobs.add(job2);

        ArrayList<Job> JobsCorrectOrder = new ArrayList<Job>();
        JobsCorrectOrder.add(job);
        JobsCorrectOrder.add(job1);
        JobsCorrectOrder.add(job2);

        SortingStrategy sbl = new SortByLocationStrategy(userLoc);


//        ArrayList<Job> actually_sorted = new ArrayList<Job>();
//        actually_sorted.add(job1);
//        actually_sorted.add(job2);
//        actually_sorted.add(job);

        ArrayList<Job> sorted_jobs_boi = sbl.sort(jobs);
        for (Job jobz : sorted_jobs_boi) {
            System.out.println(jobz.getLocation());
        }

        assertEquals(sorted_jobs_boi, JobsCorrectOrder);

    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.gigpig", appContext.getPackageName());
    }
}
