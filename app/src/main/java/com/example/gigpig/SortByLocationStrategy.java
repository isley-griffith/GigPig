package com.example.gigpig;
import android.location.Location;

import java.util.*;

public class SortByLocationStrategy implements SortingStrategy {
    private Location userLoc;
    public class SortByName implements Comparator<Job> {
        public int compare(Job job, Job job2) {
            Float dist1 = job.getLocation().distanceTo(userLoc);
            Float dist2 = job2.getLocation().distanceTo(userLoc);
            return dist1.compareTo(dist2);
        }
    }

    public SortByLocationStrategy(Location userLoc){
        this.userLoc = userLoc;
    }

    public ArrayList<Job> sort(ArrayList<Job> jobs) {
        ArrayList<Job> locationsOfJobs = new ArrayList<Job>(jobs);
        Collections.sort(locationsOfJobs, new SortByName());
        return locationsOfJobs;
    }

}
