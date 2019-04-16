package com.example.gigpig;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    private User currentUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference("users/" + mAuth.getUid());

        dataRef.addValueEventListener(this);

        return inflater.inflate(R.layout.fragment_profile, null);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        // This method is called once with the initial value and again
        // whenever data at this location is updated.
        if (dataSnapshot == null)
            return;
        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            if (snapshot == null) return;
            this.currentUser = snapshot.getValue(User.class);
        }

        this.nameField = getView().findViewById(R.id.nameField);
        this.usernameField = getView().findViewById(R.id.usernameField);
        this.phonenumberField = getView().findViewById(R.id.phonenumberField);
        this.interestsField = getView().findViewById(R.id.interestsField);
        this.bioField = getView().findViewById(R.id.bioField);

        this.nameField.setText(this.currentUser.getFirstName() + " " + this.currentUser.getLastName());
        this.usernameField.setText(this.currentUser.getUsername());
        this.phonenumberField.setText(this.currentUser.getPhoneNum());
        this.interestsField.setText("Interests: " + this.currentUser.getTags().toString());
        this.bioField.setText(this.currentUser.getBio());
    }

    @Override
    public void onCancelled(DatabaseError error) {
        // Failed to read value
        System.out.println("Failed to read value." + error.toException());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
