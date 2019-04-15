package com.example.gigpig;
import java.util.*;

/**
 * Notification class
 * Not yet implemented
 */
public class Notification {
    private ArrayList<Job> notifications = new ArrayList<Job>();

    /**
     * Add a notification to the list of notifications
     * @param job
     */
    public void addNotification(Job job) {
        notifications.add(job);
    }
}
