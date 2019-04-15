package com.example.gigpig;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;

public class SortByAlphabeticalOrder implements SortingStrategy {
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
            return job.getJobTitle().compareTo(job2.getJobTitle());
        }
    }
    /**
     * @param jobs
     * @return ArrayList<Job> that is sorted based on the compare method (by alphabetical order)
     */
    public ArrayList<Job> sort(ArrayList<Job> jobs) {
        ArrayList<Job> modifiedJobs = new ArrayList<Job>(jobs);
        Collections.sort(modifiedJobs, new SortByName());
        return modifiedJobs;
    }
}