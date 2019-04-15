package com.example.gigpig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortByHighestPrice implements SortingStrategy {
    /**
     * Comparator method that sets the rules for the sort function
     */
    public class SortByName implements Comparator<Job> {
        /**
         * @param job
         * @param job2
         * @return an int (-1, 0, 1)
         */
        public int compare(Job job, Job job2) {
            return job2.getPayout().compareTo(job.getPayout());
        }
    }
    /**
     * @param jobs
     * @return ArrayList<Job> that is sorted based on the compare method (by highest price job)
     */
    public ArrayList<Job> sort(ArrayList<Job> jobs) {
        ArrayList<Job> modifiedJobs = new ArrayList<Job>(jobs);
        Collections.sort(modifiedJobs, new SortByHighestPrice.SortByName());
        return modifiedJobs;
    }

}
