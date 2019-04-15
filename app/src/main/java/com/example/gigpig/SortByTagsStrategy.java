package com.example.gigpig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class SortByTagsStrategy implements SortingStrategy {

    private ArrayList<String> tags;
    public SortByTagsStrategy(ArrayList<String> tags) {
            this.tags = tags;
    }
    public ArrayList<Job> sort(ArrayList<Job> jobs) {

        ArrayList<Job> onlyTaggedJobs = new ArrayList<>();

        for (Job j : jobs) {
            for (String word : tags)
                if (j.getTags().contains(word)) // we want this job
                    if (!onlyTaggedJobs.contains(j))
                        onlyTaggedJobs.add(j);
        }
        return onlyTaggedJobs;
    }
}

//        onlyTaggedJobs.removeAll(Collections.singleton(null));
//        sortedJobs.addAll(jobs);
//        Collections.sort(sortedJobs, new SortByTags(tags));