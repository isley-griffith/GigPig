package com.example.gigpig;

import android.location.Location;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SortByDateStrategyTest {

    @Test
    public void testSortDate() {
        ArrayList<Job> jobs = new ArrayList<Job>();
        ArrayList<String> tags = new ArrayList<String>();


        ArrayList<Job> dates = new ArrayList<Job>();
        ArrayList<Job> og = new ArrayList<Job>();
        Location dummyLoc = new Location("dummy location");



        ArrayList<Job> actually_sorted = new ArrayList<Job>();

        User user = new User();

        try {
            Job job = new Job("YOOOOO WASH MY stuff", "needa wash buddy", 3.20, user, tags, dummyLoc);;
            Thread.sleep(2000);
            Job job1 = new Job("AAAAYOOOOO WASH MY stuff", "needa wash buddy", 3.20, user, tags, dummyLoc);
            Thread.sleep(2000);
            Job job2 = new Job("BAAYOOOOO WASH MY stuff", "needa wash buddy", 3.20, user, tags, dummyLoc);
            jobs.add(job);
            jobs.add(job2);
            jobs.add(job1);

            actually_sorted.add(job);
            actually_sorted.add(job1);
            actually_sorted.add(job2);

        } catch (InterruptedException ex) {
            System.out.println("Interrupted exception");
        }

        ArrayList<Job> jobsShouldntBeModified = new ArrayList<Job>(jobs);
        SortingStrategy sbd = new SortByDateStrategy();

        System.out.println("og dates: ");
        for (Job jobz : jobs) {
            System.out.println(jobz.getCreationDate());
        }

        ArrayList<Job> sorted_jobs_boi = sbd.sort(jobs);
        System.out.println("sorted_jobs_boi dates: ");
        for (Job jobz : sorted_jobs_boi) {
            System.out.println(jobz.getCreationDate());
        }

        assertEquals(sorted_jobs_boi, actually_sorted);
        // assertEquals(jobsShouldntBeModified, jobs);

    }


}