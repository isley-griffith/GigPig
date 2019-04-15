package com.example.gigpig;
import java.util.ArrayList;

/**
 * Interface for strategies that sort jobs
 */
public interface SortingStrategy {
    public ArrayList<Job> sort(ArrayList<Job> jobs);
}
