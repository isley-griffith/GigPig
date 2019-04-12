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

public class HomeFragment extends Fragment {

//    private ArrayList<Posting> postingList;
    private ArrayList<Job> jobsList;

    private RecyclerView recyclerView;
//    private PostingAdapter mAdapter;
    private  JobAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

//        this.postingList = new ArrayList<Posting>();
        this.jobsList = new ArrayList<Job>();


        return view;
//        return inflater.inflate(R.layout.fragment_home, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = getView().findViewById(R.id.recycler_view);

//        mAdapter = new PostingAdapter(postingList);
        mAdapter = new JobAdapter(jobsList);
        mAdapter.notifyDataSetChanged();


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        preparePostings();
    }

    private void preparePostings() {
//        Posting post = new Posting("Niel", "need my rug washed", "your moms house");

        // placeholder
        User user = new User();
        ArrayList<String> tags = new ArrayList<>();
        tags.add("goat");
        tags.add("wash");

        Job job = new Job("Goat wash",
                "Need my goat washed. Someone with strong hands prefered to get all the gunk out of him",
                12, user, tags);

//        this.postingList.add(post);
        this.jobsList.add(job);

        job = new Job("Long ass text test",
                "Need my goatfdjkfhjdkshfjkldhflakjdhsf" +
                        "dafhladskjhfakljdshfjlkadshflasdkjfhlads" +
                        "adhfkjlhasdklfjhasdlkfjhasdlfjkha" +
                        "" +
                        "fjksdfjkashdflaksdfladsfhdlskjfhdsalk" +
                        "fhsdlakjhfadsf" +
                        "hfjaskdlfhkldsjfh" +
                        "fhdsajkfhasldjfhsladfhkjsdlafkhljksdafhdsalkhfjalsdkhfj" +
                        " washed. Someone with strong hands prefered to get all the gunk out of him",
                12, user, tags);

        this.jobsList.add(job);

        tags.clear();
        tags.add("find");
        tags.add("ants");
        tags.add("lost");

        job = new Job("Lost ants",
                "REWARD: Lost my whole colony of ants. Please help me find them. They respond to 'little boys'",
                100, user, tags);

        this.jobsList.add(job);




        mAdapter.notifyDataSetChanged();
    }

}
