package com.example.gigpig;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


/**
 * Class to write objects to the database
 */
public class DatabaseHelper {


    /**
     * Writes a job to the firebase database
     * @param job The job that will be written to the database
     */
    public static void writeNewJob(Job job) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.child("jobs").push().setValue(job);
    }

    /**
     * Writes a user to the firebase database
     * @param user
     */
    public static void writeNewUser(User user) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");
        Map<String, User> users = new HashMap<>();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String uId = mAuth.getUid();
        users.put(uId, user);
    }
}
