package com.example.gigpig;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ArrayList<Posting> postingList;
    private RecyclerView recyclerView;
    private PostingAdapter mAdapter;
    private Context context;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        this.postingList = new ArrayList<Posting>();



        return view;
//        return inflater.inflate(R.layout.fragment_home, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = getView().findViewById(R.id.recycler_view);

        Posting post = new Posting("Niel", "need my rug washed", "your moms house");
        this.postingList.add(post);

        mAdapter = new PostingAdapter(postingList);
        mAdapter.notifyDataSetChanged();


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

//        preparePostings();
    }

    private void preparePostings() {
        Posting post = new Posting("Niel", "need my rug washed", "your moms house");
        this.postingList.add(post);

        mAdapter.notifyDataSetChanged();
    }

}
