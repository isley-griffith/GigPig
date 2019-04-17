package com.example.gigpig;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class to help put jobs in list
 */
public class UserJobAdapter extends RecyclerView.Adapter<UserJobAdapter.MyViewHolder> {

    /**
     * List of jobs to be displayed on the profile page
     */
    private ArrayList<Job> jobsList;
    private View.OnClickListener clickListener;

    /**
     * View representing an individual cell on the profile page
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title, description, price, tags, date;
        private Job jobTappedOn;
        private int positionJob;

        public void setPositionJob(int position) {
            this.positionJob = position;
        }

        public Job getJob() {
            return this.jobTappedOn;
        }

        public void setJob(Job job) {
            this.jobTappedOn = job;
        }

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title_user);
            description = view.findViewById(R.id.description_user);
            price = view.findViewById(R.id.price_user);
            tags = view.findViewById(R.id.tags_user);
            date = view.findViewById(R.id.date_user);

            // calls when you tap on a button
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    jobOnClick(view, jobTappedOn, positionJob);
                }
            });
        }

    }

    /**
     * Called when the user taps on a job
     * @param v the view we are working with
     * @param job the job tapped on
     */
    private void jobOnClick(View v, final Job job, int position) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
        alertDialog.setTitle("Delete entry");
        alertDialog.setMessage("Are you sure you want to delete this entry?");
        final String jobTitle = job.getJobTitle();
        final String inquirerId = job.getInquirerId();

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
        alertDialog.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseHelper.deleteJobWithTitleFromUser(jobTitle, inquirerId);
                    }
                });

                // A null listener allows the button to dismiss the dialog and take no further action.
        alertDialog.setNegativeButton(android.R.string.no, null);
        alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
        alertDialog.show();
    }

    /**
     * Used for instantiating the view with the list of jobs we would like to display
     * @param jobsList
     */
    public UserJobAdapter(ArrayList<Job> jobsList) {
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
     * Called when the RecyclerView on the profile page instantiates a job cell item
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
        holder.setPositionJob(position);
    }

    /**
     * Called when a new holder is created
     * @return our cell specific to the job we would like to display
     */
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.job_list_row_user, parent, false);

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
