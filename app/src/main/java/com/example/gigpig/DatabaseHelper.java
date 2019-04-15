package com.example.gigpig;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DatabaseHelper {

    /**
     * Writes a job to the firebase database
     * @param job The job that will be written to the database
     */
    public static void writeNewJob(Job job) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.child("jobs").push().setValue(job);
    }

}
