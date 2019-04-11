package com.example.gigpig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import com.google.android.material.internal.*;

public class SortByTagsStrategy implements SortingStrategy {
    String tags;
    DamerauLevenshteinAlgorithm dla;

    public SortByTagsStrategy(String tags) {
        this.tags = tags;
        dla = new DamerauLevenshteinAlgorithm();
    }

    public ArrayList<Job> sort(ArrayList<Job> jobs) {
        Multimap<Integer, Job> jobsWithValue = new HashMap<Job, Integer>();

        Multimap

        for(Job job : jobs) {
            Integer dlaValue = dla.execute(tags, job.getTags());
            jobsWithValue.put(job, dlaValue);
        }

        ArrayList<Job> sortedJobs = new ArrayList<Job>();

        Iterator it = jobsWithValue.entrySet().iterator();


        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Job key = (Job) pair.getKey();
            Integer value = (Integer) pair.getValue();
            if(value.compareTo())
        }
    }
}
