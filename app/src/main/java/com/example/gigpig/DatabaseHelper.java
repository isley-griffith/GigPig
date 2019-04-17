package com.example.gigpig;

import android.support.annotation.NonNull;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    /**
     * User can choose to delete a job from their own postings in their profile page.
     * @param title
     * @param uId
     */
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
}
