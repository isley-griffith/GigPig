package com.example.gigpig;

import android.support.annotation.NonNull;

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
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        usersRef.child("users").child(mAuth.getUid()).setValue(user);
    }

    public static void deleteJobWithTitleFromUser(final String title, final String uId) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("jobs");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (snapshot.child("inquirerId").getValue(String.class).equals(uId)
                        && snapshot.child("jobTitle").getValue(String.class).equals(title)) {
                        System.out.println("removing " + snapshot.getValue() + " from database");
                        snapshot.getRef().removeValue();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /**
     * Fetches jobs that the currently logged in user did not create
     * @return arraylist of jobs made by the current user
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
                if (dataSnapshot == null) return;

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Job job = snapshot.getValue(Job.class);
                    System.out.println(job + job.getJobTitle());

                    if (!(job.getInquirerId().equals(uId)))
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

    /**
     * Fetches jobs that the currently logged in user created
     * @return arraylist of jobs made by the current user
     */
    public static ArrayList<Job> getUsersPostedJobs() {
        final ArrayList<Job> profileJobs = new ArrayList<Job>();
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final String uId = mAuth.getUid();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("jobs");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot == null) return;

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Job job = snapshot.getValue(Job.class);
                    System.out.println(job + job.getJobTitle());

                    if (job.getInquirerId().equals(uId))
                        profileJobs.add(job);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        return profileJobs;
    }

    public static String getUsername(final String uId) {
        System.out.println("GetUsernamePASSEDuID:" + uId);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final String username = "BUNGUS";
        DatabaseReference ref = database.getReference("users");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot == null) return;

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    System.out.println("USER:" + user.getuId());

                    if (user.getuId().equals(uId))
                        System.out.println("FOUND THAT " + uId + " IS EQUAL TO " + user.getuId());
                        username.concat(user.getUsername());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        System.out.println("RETURNING: " + username);
        return username;
    }

    public static String getNumber(final String uId) {
        System.out.println("GetNumPASSEDuID:" + uId);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("users");
        final String phoneNum = "";
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot == null) return;

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    System.out.println("USER:" + user.getuId());

                    if (user.getuId().equals(uId))
                        System.out.println("FOUND THAT " + uId + " IS EQUAL TO " + user.getuId());
                        phoneNum.concat(user.getPhoneNum());
                        
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        System.out.println("RETURNING: " + phoneNum);
        return phoneNum;
    }
}
