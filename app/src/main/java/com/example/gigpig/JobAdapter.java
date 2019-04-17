package com.example.gigpig;

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

    private ArrayList<Job> jobsList;

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
                    System.out.println("Clicked Clicked Clicked");
                }
            });
        }

    }

    public JobAdapter(ArrayList<Job> jobsList) {
        this.jobsList = jobsList;
    }

    public void updateContents(ArrayList<Job> jobsList) {
        this.jobsList = jobsList;
    }


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

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.job_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return jobsList.size();
    }
}
