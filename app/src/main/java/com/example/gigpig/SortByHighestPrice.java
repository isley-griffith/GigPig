package com.example.gigpig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortByHighestPrice implements SortingStrategy {
    public class SortByName implements Comparator<Job> {
        public int compare(Job job, Job job2) {
            return job2.getPayout().compareTo(job.getPayout());
        }
    }
    public ArrayList<Job> sort(ArrayList<Job> jobs) {
        ArrayList<Job> modifiedJobs = new ArrayList<Job>(jobs);
        Collections.sort(modifiedJobs, new SortByHighestPrice.SortByName());
        return modifiedJobs;
    }

}
