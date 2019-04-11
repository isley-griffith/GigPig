package com.example.gigpig;
import java.util.*;

public class Notification {
    private ArrayList<Job> notifications = new ArrayList<Job>();

    public void addNotification(Job job) {
        notifications.add(job);
    }
}
