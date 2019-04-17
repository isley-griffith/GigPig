package com.example.gigpig;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Class to write objects to the database
 */
public class DatabaseHelper {


    /**
     * Writes a job to the firebase databasex
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

    /**
     * Fetches jobs that the currently logged in user did not create
     * @return arraylist of jobs more relevent to the current user
     */
    public static ArrayList<Job> getPostedJobsForUser() {
        final ArrayList<Job> homeScreenJobs = new ArrayList<Job>();
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final String uId = mAuth.getUid();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("jobs");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Job job = dataSnapshot.getValue(Job.class);
                if((job.getInquirerId() != uId) && !(job.isComplete()) && !(job.isTaken())) {
                    homeScreenJobs.add(job);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        return homeScreenJobs;
    }

    public static String getUsername(final String uId) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("users");
        final String username = "";
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if(user.getuId().equals(uId)) {
                    username.concat(user.getUsername());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        return username;
    }
}
