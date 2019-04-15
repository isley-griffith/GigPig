package com.example.gigpig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortByLowestPrice implements SortingStrategy {
    public class SortByName implements Comparator<Job> {
        public int compare(Job job, Job job2) {
            return job.getPayout().compareTo(job2.getPayout());
        }
    }
    public ArrayList<Job> sort(ArrayList<Job> jobs) {
            ArrayList<Job> modifiedJobs = new ArrayList<Job>(jobs);
            Collections.sort(modifiedJobs, new SortByLowestPrice.SortByName());
            return modifiedJobs;
    }

}
