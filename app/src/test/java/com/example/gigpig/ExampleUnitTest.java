package com.example.gigpig;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testSortAlphabetically() {
        ArrayList<Job> jobs = new ArrayList<Job>();

        User user = new User();

        Job job = new Job("YOOOOO WASH MY SHIT", "needa wash buddy", 3.20, user);
        Job job1 = new Job("AAAAYOOOOO WASH MY SHIT", "needa wash buddy", 3.20, user);
        Job job2 = new Job("BAAYOOOOO WASH MY SHIT", "needa wash buddy", 3.20, user);

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

    @Test
    public void testSortByTags() {

        ArrayList<Job> jobs = new ArrayList<Job>();

        User user = new User();
        user.setTags("wash clean");

        Job job = new Job("wash goat", "needa wash buddy", 3.20, user);
        job.addTags("wash");

        Job job1 = new Job("clean floor", "needa wash buddy", 3.20, user);
        job1.addTags("clean");
        Job job2 = new Job("wash my cleaning supplies", "needa wash buddy", 3.20, user);
        job2.addTags("wash clean");
        Job job3 = new Job("buy my dad", "needa wash buddy", 3.20, user);
        job2.addTags("nothing");


        jobs.add(job);
        jobs.add(job1);
        jobs.add(job2);
        jobs.add(job3);

        SortingStrategy sbt = new SortByTagsStrategy(user.getTags());

        ArrayList<Job> toSort = sbt.sort(jobs);

        System.out.println("before:");

        for (Job jobz : jobs)
            System.out.println(jobz.getTitle());

        System.out.println();
        System.out.println("after: ");
        for (Job jobz : toSort)
            System.out.println(jobz.getTitle());

    }
}