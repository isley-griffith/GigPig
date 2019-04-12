package com.example.gigpig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class SortByTagsStrategy implements SortingStrategy {

//    public class SortByTags implements Comparator<Job> {
//        String tags;
//
//        public SortByTags(String tags) {
//            this.tags = tags;
//        }
//
//        public int compare(Job job, Job job2) {
//            //DamerauLevenshteinAlgorithm dla = new DamerauLevenshteinAlgorithm(1,1,1,1);
//            //return dla.execute(tags, job2.getTags());
//            return job.getTags().compareTo(job2.getTags());
//
//
//        }
//    }

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