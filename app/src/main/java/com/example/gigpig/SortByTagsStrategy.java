package com.example.gigpig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class SortByTagsStrategy implements SortingStrategy {

    public class SortByTags implements Comparator<Job> {
        String tags;

        public SortByTags(String tags) {
            this.tags = tags;
        }

        public int compare(Job job, Job job2) {
            DamerauLevenshteinAlgorithm dla = new DamerauLevenshteinAlgorithm(1,1,1,1);
            return dla.execute(tags, job2.getTags());
        }
    }

    String tags;

    public SortByTagsStrategy(String tags) {
        this.tags = tags;
    }

    public ArrayList<Job> sort(ArrayList<Job> jobs) {
        ArrayList<Job> sortedJobs = new ArrayList<>();
        sortedJobs.addAll(jobs);
        Collections.sort(sortedJobs, new SortByTags(tags));
        return sortedJobs;
    }
}

