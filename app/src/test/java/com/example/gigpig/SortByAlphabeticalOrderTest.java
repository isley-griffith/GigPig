package com.example.gigpig;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SortByAlphabeticalOrderTest {

    @Test
    public void testSortAlphabetically() {
        ArrayList<Job> jobs = new ArrayList<Job>();
        ArrayList<String> tags = new ArrayList<String>();
        User user = new User();

        Job job = new Job("YOOOOO WASH MY stuff", "needa wash buddy", 3.20, user, tags, null);
        Job job1 = new Job("AAAAYOOOOO WASH MY stuff", "needa wash buddy", 3.20, user, tags, null);
        Job job2 = new Job("BAAYOOOOO WASH MY stuff", "needa wash buddy", 3.20, user, tags, null);

        jobs.add(job);
        jobs.add(job1);
        jobs.add(job2);

        ArrayList<Job> jobsShouldntBeModified = new ArrayList<Job>(jobs);

        SortingStrategy sao = new SortByAlphabeticalOrder();


        ArrayList<Job> actually_sorted = new ArrayList<Job>();
        actually_sorted.add(job1);
        actually_sorted.add(job2);
        actually_sorted.add(job);

        ArrayList<Job> sorted_jobs_boi = sao.sort(jobs);

        assertEquals(sorted_jobs_boi, actually_sorted);
        assertEquals(jobsShouldntBeModified, jobs);

    }


}