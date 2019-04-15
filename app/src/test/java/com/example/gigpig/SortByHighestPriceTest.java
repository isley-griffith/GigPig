package com.example.gigpig;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SortByHighestPriceTest {

    @Test
    public void testSortByHighestPrice() {
        ArrayList<Job> jobs = new ArrayList<Job>();
        ArrayList<String> tags = new ArrayList<String>();
        User user = new User();

        Job job = new Job("YOOOOO WASH MY stuff", "needa wash buddy", 3.20, user, tags, null);
        Job job1 = new Job("AAAAYOOOOO WASH MY stuff", "needa wash buddy", 3.19, user, tags, null);
        Job job2 = new Job("BAAYOOOOO WASH MY stuff", "needa wash buddy", 3.21, user, tags, null);

        jobs.add(job);
        jobs.add(job1);
        jobs.add(job2);

       //ArrayList<Job> jobsShouldntBeModified = new ArrayList<Job>(jobs);

        SortingStrategy sblp = new SortByHighestPrice();


        ArrayList<Job> actually_sorted = new ArrayList<Job>();
        actually_sorted.add(job2);
        actually_sorted.add(job);
        actually_sorted.add(job1);



        ArrayList<Job> sorted_jobs_boi = sblp.sort(jobs);

        assertEquals(sorted_jobs_boi, actually_sorted);
        //assertEquals(jobsShouldntBeModified, jobs);

    }


}