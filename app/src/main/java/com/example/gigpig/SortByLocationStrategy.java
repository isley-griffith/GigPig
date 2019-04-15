package com.example.gigpig;
import android.location.Location;

import java.util.*;

/**
 * Strategy to sort jobs by closest location
 */
public class SortByLocationStrategy implements SortingStrategy {
    private Location userLoc;
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
            Float dist1 = job.getLocation().distanceTo(userLoc);
            Float dist2 = job2.getLocation().distanceTo(userLoc);
            return dist1.compareTo(dist2);
        }
    }

    public SortByLocationStrategy(Location userLoc){
        this.userLoc = userLoc;
    }
    /**
     * @param jobs
     * @return ArrayList<Job> that is sorted based on the compare method (by closest job location)
     */
    public ArrayList<Job> sort(ArrayList<Job> jobs) {
        ArrayList<Job> locationsOfJobs = new ArrayList<Job>(jobs);
        Collections.sort(locationsOfJobs, new SortByName());
        return locationsOfJobs;
    }

}
