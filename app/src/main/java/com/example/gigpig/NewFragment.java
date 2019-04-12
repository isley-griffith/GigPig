package com.example.gigpig;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NewFragment extends Fragment {

    TextView newLable;
    TextView success;

    EditText jobTitleInput;
    EditText jobDescriptionInput;
    EditText jobPriceInput;

    Button createJobButton;

    Job newJob;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.newLable = getView().findViewById(R.id.newLabel);
        this.newLable.setText("NEW");

        this.success = getView().findViewById(R.id.success);

        this.createJobButton = getView().findViewById(R.id.createJobButton);
        this.createJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createJob(view);
            }
        });

        this.jobTitleInput = getView().findViewById(R.id.jobTitleInput);
        this.jobDescriptionInput = getView().findViewById(R.id.jobDescriptionInput);
        this.jobPriceInput = getView().findViewById(R.id.jobPriceInput);

        this.newJob = null;

    }

    // TODO: Some database stuff
    private void createJob(View view) {
        double payout = Float.valueOf(this.jobPriceInput.getText().toString());
        String description = this.jobDescriptionInput.getText().toString();
        String title = this.jobTitleInput.getText().toString();

        // placeholder
        User placeHolderUser = new User();

        this.newJob = new Job(title, description, payout, placeHolderUser);

        this.success.setText("Created new job");
    }
}
