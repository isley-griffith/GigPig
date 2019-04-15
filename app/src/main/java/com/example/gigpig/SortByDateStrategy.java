package com.example.gigpig;

import com.example.gigpig.Job;
import com.example.gigpig.SortingStrategy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class SortByDateStrategy implements SortingStrategy {
    public class SortByDate implements Comparator<Job> {
        public int compare(Job job, Job job2) {
            return job2.getCreationDate().compareTo(job.getCreationDate());
        }
    }

    public ArrayList<Job> sort(ArrayList<Job> jobs) {
        ArrayList<Job> sortedDates = new ArrayList<Job>(jobs);
        Collections.sort(sortedDates, new SortByDateStrategy.SortByDate());
        return sortedDates;
    }
}
