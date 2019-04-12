package com.example.gigpig;

import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;

import java.util.ArrayList;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.MyViewHolder> {

    private ArrayList<Job> jobsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description, price;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            description = (TextView) view.findViewById(R.id.description);
            price = (TextView) view.findViewById(R.id.price);
        }
    }

    public JobAdapter(ArrayList<Job> jobsList) {
        this.jobsList = jobsList;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Job job = jobsList.get(position);

        holder.title.setText(job.getTitle());
        holder.description.setText(job.getDescription());
        holder.price.setText("$" + String.format("%.2f", job.getPayout()));


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
