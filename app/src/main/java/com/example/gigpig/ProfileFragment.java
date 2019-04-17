package com.example.gigpig;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Fragment to manage profile fragment
 */
public class ProfileFragment extends Fragment implements ValueEventListener {

    private TextView nameField;
    private TextView usernameField;
    private TextView phonenumberField;
    private TextView interestsField;
    private TextView bioField;
    private Button signoutbutton;
    private FirebaseAuth mAuth;

    private RecyclerView recyclerView;
    private UserJobAdapter mAdapter;

    private ArrayList<Job> userJobList;

    private User currentUser;

    String uId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        this.uId = mAuth.getUid();
        String path = "users";
        DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference(path);

        this.userJobList = new ArrayList<>();

        dataRef.addValueEventListener(this);

        return inflater.inflate(R.layout.fragment_profile, null);
    }

    public void signOutButtonClicked(View v) {
        mAuth.signOut();
        Intent i = new Intent(v.getContext(), MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        // This method is called once with the initial value and again

        if (dataSnapshot == null) return;

        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            User user = snapshot.getValue(User.class);
            System.out.println(user + user.getuId());
            if (user.getuId() == null)
                continue;

            if (user.getuId().equals(uId))
                this.currentUser = user;
        }

        if (this.currentUser != null) {
            this.nameField.setText(this.currentUser.getFirstName() + " " + this.currentUser.getLastName());
            this.usernameField.setText(this.currentUser.getUsername());
            this.phonenumberField.setText(this.currentUser.getPhoneNum());
            this.interestsField.setText("Interests: " + this.currentUser.getTags().toString());
            this.bioField.setText(this.currentUser.getBio());

        }
    }


    @Override
    public void onCancelled(DatabaseError error) {
        // Failed to read value
        System.out.println("Failed to read value." + error.toException());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.nameField = getView().findViewById(R.id.nameField);
        this.usernameField = getView().findViewById(R.id.usernameField);
        this.phonenumberField = getView().findViewById(R.id.phonenumberField);
        this.interestsField = getView().findViewById(R.id.interestsField);
        this.bioField = getView().findViewById(R.id.bioField);

        this.recyclerView = getView().findViewById(R.id.recycler_view_profile);

//        this.userJobList = DatabaseHelper.getUsersPostedJobs();

        mAdapter = new UserJobAdapter(userJobList);

        mAdapter.notifyDataSetChanged();

        recyclerView = getView().findViewById(R.id.recycler_view_profile);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference("jobs");

        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (currentUser.getuId() == null) return;
                userJobList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Job job;
                    if (snapshot.child("inquirerId").getValue(String.class).equals(currentUser.getuId())) {
                        job = snapshot.getValue(Job.class);
                        userJobList.add(job);
                    }
                }


                for (Job job : userJobList)
                    if (job.getCreationDate() == null || job.getJobTitle() == null)
                        return;

                mAdapter.updateContents(userJobList);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        signoutbutton = getView().findViewById(R.id.signoutbutton);
        signoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOutButtonClicked(v);
            }
        });
    }
}
