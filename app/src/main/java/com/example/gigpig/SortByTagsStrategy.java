package com.example.gigpig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class SortByTagsStrategy implements SortingStrategy {

    public class SortByTags implements Comparator<Job> {
        public int compare(Job job, Job job2) {
            DamerauLevenshteinAlgorithm dla = new DamerauLevenshteinAlgorithm(1,1,1,1);
            return dla.execute(job.getTags(), job2.getTags());
        }
    }


    public SortByTagsStrategy() {

    }

    public ArrayList<Job> sort(ArrayList<Job> jobs) {
        Collections.sort(jobs, new SortByTags());
        return jobs;
    }
}

