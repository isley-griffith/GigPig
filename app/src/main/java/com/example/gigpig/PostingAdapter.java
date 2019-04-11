package com.example.gigpig;

import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;

import java.util.List;

public class PostingAdapter extends RecyclerView.Adapter<PostingAdapter.MyViewHolder> {

    private List<Posting> postingList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView poster, description, location;

        public MyViewHolder(View view) {
            super(view);
            poster = (TextView) view.findViewById(R.id.poster);
            description = (TextView) view.findViewById(R.id.description);
            location = (TextView) view.findViewById(R.id.location);
        }
    }

    public PostingAdapter(List<Posting> postingList) {
        this.postingList = postingList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.posting_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Posting post = postingList.get(position);
        holder.poster.setText(post.poster);
        holder.description.setText(post.description);
        holder.location.setText(post.location);

    }

    @Override
    public int getItemCount() {
        return postingList.size();
    }
}
