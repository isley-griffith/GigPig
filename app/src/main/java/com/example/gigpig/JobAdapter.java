package com.example.gigpig;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.text.method.Touch;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class to help put jobs in list
 */
public class JobAdapter extends RecyclerView.Adapter<JobAdapter.MyViewHolder> {

    /**
     * List of jobs to be displayed on the home screen
     */
    private ArrayList<Job> jobsList;

    /**
     * View representing an individual cell on the Home page
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description, price, tags, date;
        private Job jobTappedOn;

        public Job getJob() {
            return this.jobTappedOn;
        }

        public void setJob(Job job) {
            this.jobTappedOn = job;
        }

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            description = view.findViewById(R.id.description);
            price = view.findViewById(R.id.price);
            tags = view.findViewById(R.id.tags);
            date = view.findViewById(R.id.date);

            // calls when you tap on a button
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    jobOnClick(view, jobTappedOn);
                }
            });
        }

    }

    /**
     * Called when the user taps on a job on the home screen
     * @param v the view we are working with
     * @param job the job tapped on
     */
    private void jobOnClick(View v, Job job) {
        Intent specificJob = new Intent(v.getContext(), JobViewActivity.class);
        Bundle b = new Bundle();
        specificJob.putExtra("jobTappedOn", job);
        v.getContext().startActivity(specificJob);
    }

    /**
     * Used for instantiating the view with the list of jobs we would like to display
     * @param jobsList
     */
    public JobAdapter(ArrayList<Job> jobsList) {
        this.jobsList = jobsList;
    }

    /**
     * Update the jobs list to reflect the specific information we would like to display
     * @param jobsList
     */
    public void updateContents(ArrayList<Job> jobsList) {
        this.jobsList = jobsList;
    }

    /**
     * Called when the RecyclerView on the Home page instantiates a job cell item
     * @param holder The cell to be binded to the displayed list
     * @param position The position in the list the cell will be displayed
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (jobsList.get(position) == null) return;
        Job job = jobsList.get(position);

        holder.title.setText(job.getJobTitle());
        holder.description.setText(job.getDescription());
        holder.tags.setText(job.getTags().toString());

        Date date = new Date(job.getCreationDate());
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yy HH:mm");
        String displayDate = "Listed on " + formatDate.format(date);

        holder.date.setText(displayDate);

        holder.price.setText("$" + String.format("%.2f", job.getPayout()));

        holder.setJob(job);
    }

    /**
     * Called when a new holder is created
     * @return our cell specific to the job we would like to display
     */
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.job_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    /**
     * @return the amount of cells to be displayed
     */
    @Override
    public int getItemCount() {
        return jobsList.size();
    }
}
