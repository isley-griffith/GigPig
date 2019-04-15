package com.example.gigpig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Strategy to sort jobs by lowest price
 */
public class SortByLowestPrice implements SortingStrategy {
    /**
     * Comparator method that sets the rules for the sort function
     */
    public class SortByName implements Comparator<Job> {
        /**
         *
         * @param job
         * @param job2
         * @return an int (-1, 0, 1)
         */
        public int compare(Job job, Job job2) {
            return job.getPayout().compareTo(job2.getPayout());
        }
    }

    /**
     * @param jobs
     * @return ArrayList<Job> that is sorted based on the compare method (by lowest price job)
     */
    public ArrayList<Job> sort(ArrayList<Job> jobs) {
            ArrayList<Job> modifiedJobs = new ArrayList<Job>(jobs);
            Collections.sort(modifiedJobs, new SortByLowestPrice.SortByName());
            return modifiedJobs;
    }

}
