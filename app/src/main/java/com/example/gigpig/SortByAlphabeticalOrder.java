package com.example.gigpig;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;

public class SortByAlphabeticalOrder implements SortingStrategy {
    public class SortByName implements Comparator<Job> {
        public int compare(Job job, Job job2) {
            return job.getTitle().compareTo(job2.getTitle());
        }
    }

    public ArrayList<Job> sort(ArrayList<Job> jobs) {
        ArrayList<Job> modifiedJobs = new ArrayList<Job>(jobs);
        Collections.sort(modifiedJobs, new SortByName());
        return modifiedJobs;
    }

}

