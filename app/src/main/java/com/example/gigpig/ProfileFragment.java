package com.example.gigpig;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    TextView nameField;
    TextView usernameField;
    TextView phonenumberField;
    TextView interestsField;
    TextView bioField;

    User currentUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ArrayList<String> tags = new ArrayList<>();
        tags.add("goat");
        tags.add("bmx");

        this.currentUser = new User("Stefan",
                "Bay",
                "410-570-0592",
                "stefan_bay",
                tags,
                "I like goats + bmx\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nsooooojfghsdghjgdjfghjsagfjhkgdsakjhghggfhdghjfdgsjdgfkhjsdgfkjsdhfgdsjkhgfshdgfsgdfjhsdfgjhsdghjsdgfjkhsdghskjdgfjhsgdhjks");


        return inflater.inflate(R.layout.fragment_profile, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
}
