package com.example.gigpig;

import android.location.Location;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SortByTagsTest {

    @Test
    public void testSortByTags() {




        ArrayList<Job> jobs = new ArrayList<Job>();
        ArrayList<String> tags = new ArrayList<String>();
        User user = new User();
        user.addTag("nothing");
        user.addTag("wash");
        user.addTag("clean");
        user.addTag("hello");
        tags.add("wash");
        tags.add("clean");
        ArrayList<String> noTags = new ArrayList<String>();
        noTags.add("heya");
        noTags.add("pleasehelpmydad");
        Location loc = new Location("dummy");

        Job job = new Job("wash goat", "needa wash buddy", 3.20, user, tags, loc);
        Job job1 = new Job("clean floor", "needa wash buddy", 3.20, user, tags, loc);
        Job job2 = new Job("wash my cleaning supplies", "needa wash buddy", 3.20, user, tags, loc);
        Job job3 = new Job("buy my dad", "needa wash buddy", 3.20, user, noTags, loc);

        jobs.add(job);
        jobs.add(job1);
        jobs.add(job2);
        jobs.add(job3);
        ArrayList<Job> testList = new ArrayList<>();
        testList.add(job);
        testList.add(job1);
        testList.add(job2);

        SortingStrategy sbt = new SortByTagsStrategy(user.getTags());
        ArrayList<Job> toSort = sbt.sort(jobs);
        assertEquals(testList, toSort);
        assertNotEquals(toSort, jobs);

    }
}