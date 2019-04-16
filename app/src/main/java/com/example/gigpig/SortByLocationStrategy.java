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
            Location jobLoc = new Location("");
            jobLoc.setLatitude(job.getLocation().latitude);
            jobLoc.setLongitude(job.getLocation().longitude);

            Location job2Loc = new Location("");
            jobLoc.setLatitude(job2.getLocation().latitude);
            jobLoc.setLongitude(job2.getLocation().longitude);


            Float dist1 = jobLoc.distanceTo(userLoc);
            Float dist2 = job2Loc.distanceTo(userLoc);
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
