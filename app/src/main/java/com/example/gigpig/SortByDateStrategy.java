package com.example.gigpig;

import com.example.gigpig.Job;
import com.example.gigpig.SortingStrategy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class SortByDateStrategy implements SortingStrategy {
    /**
     * Comparator method that sets the rules for the sort function
     */
    public class SortByDate implements Comparator<Job> {
        /**
         * @param job
         * @param job2
         * @return an int (-1, 0, 1)
         */
        public int compare(Job job, Job job2) {
            return job2.getCreationDate().compareTo(job.getCreationDate());
        }
    }
    /**
     * @param jobs
     * @return ArrayList<Job> that is sorted based on the compare method (by most recent)
     */
    public ArrayList<Job> sort(ArrayList<Job> jobs) {
        ArrayList<Job> sortedDates = new ArrayList<Job>(jobs);
        Collections.sort(sortedDates, new SortByDateStrategy.SortByDate());
        return sortedDates;
    }
}
