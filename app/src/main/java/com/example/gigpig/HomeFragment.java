package com.example.gigpig;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Home view fragment
 */
public class HomeFragment extends Fragment implements AdapterView.OnItemSelectedListener,
                            ValueEventListener,
                            View.OnKeyListener {
    private ArrayList<Job> jobsList;
    private ArrayList<Job> displayJobsList;

    private RecyclerView recyclerView;
    private JobAdapter mAdapter;

    private Spinner sortSelection;
    private EditText searchBar;

    private SortingStrategy sortingStrategy;

    private String searchText;
    private static final String SORT_BY_TAGS = "Sort by my tags";
    private static final String SORT_BY_ALPHABETICALLY = "Sort alphabetically";
    private static final String SORT_BY_DATE = "Sort by date";
    private static final String SORT_BY_LOWEST_PRICE = "Sort by lowest price";
    private static final String SORT_BY_HIGHEST_PRICE = "Sort by highest price";
    private static final int STARTING_SORT_BY = 2;

    private User currentUser;


    /**
     * Default android function that gets called when a view is created
     * @return An instantiation of the home screen view
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        this.jobsList = new ArrayList<Job>();
        this.sortingStrategy = new SortByDateStrategy();

        searchText = "";

        DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference("jobs");

        dataRef.addValueEventListener(this);

        final String userId = FirebaseAuth.getInstance().getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    if (user.getuId().equals(userId)) {
                        currentUser = user;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sortSelection = getView().findViewById(R.id.sortSelection);

        List<String> sortMethods = new ArrayList<>();
        sortMethods.add(SORT_BY_TAGS);
        sortMethods.add(SORT_BY_ALPHABETICALLY);
        sortMethods.add(SORT_BY_DATE);
        sortMethods.add(SORT_BY_LOWEST_PRICE);
        sortMethods.add(SORT_BY_HIGHEST_PRICE);


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, sortMethods);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sortSelection.setAdapter(dataAdapter);
        sortSelection.setSelection(STARTING_SORT_BY);

        sortSelection.setOnItemSelectedListener(this);

        mAdapter = new JobAdapter(jobsList);

        mAdapter.notifyDataSetChanged();

        recyclerView = getView().findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        searchBar = getView().findViewById(R.id.search_bar);
        searchBar.setOnKeyListener(this);
    }

    /**
     * Called on the first time the view is displayed, will call again each time the remote
     * data changes
     * @param dataSnapshot we use this to grab data from the database
     */
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        // Get Job object and use the values to update the UI

        // clears our list of jobs so that no duplicates appear
        this.jobsList.clear();


        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            Job job = snapshot.getValue(Job.class);

            if (this.searchText.equals(""))
                this.jobsList.add(job);
            else if (job.getJobTitle().contains(this.searchText) // we search based on tags and the titles of jobs
                    || job.getTags().contains(this.searchText))
                this.jobsList.add(job);

        }


        for (Job job : this.jobsList)
            if (job.getCreationDate() == null || job.getJobTitle() == null)
                return;
        this.mAdapter.updateContents(this.sortingStrategy.sort(this.jobsList));
        this.mAdapter.notifyDataSetChanged();

        // ...
    }

    /**
     * called when the database fails to fetch data
     * @param databaseError
     */
    @Override
    public void onCancelled(DatabaseError databaseError) {
        // Getting Post failed, log a message
        Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
        // ...
    }

    /**
     * Used for searching, will update the view to reflect our search query when the user
     * presses the 'Enter' key
     */
    @Override
    public boolean onKey(View view, int keyCode, KeyEvent event) {
        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                (keyCode == KeyEvent.KEYCODE_ENTER)) {
            // Perform action on key press

            this.searchText = this.searchBar.getText().toString();

            ArrayList<Job> tempJobList = new ArrayList<>();

            for (Job job : jobsList) {
                if (job.getJobTitle().toLowerCase().contains(this.searchText.toLowerCase())
                        || job.getTags().contains(this.searchText))
                    tempJobList.add(job);
            }

            if (this.searchText.equals(""))
                this.mAdapter.updateContents(this.sortingStrategy.sort(this.jobsList));
            else
                this.mAdapter.updateContents(this.sortingStrategy.sort(tempJobList));

            this.mAdapter.notifyDataSetChanged();

            return true;
        }
        return false;
    }

    /**
     * Called when spinner drop down selection is tapped
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();


        switch (item) {
            case SORT_BY_TAGS:
                this.sortingStrategy = new SortByTagsStrategy(this.currentUser.getTags());
                break;
            case SORT_BY_ALPHABETICALLY:
                this.sortingStrategy = new SortByAlphabeticalOrder();
                break;
            case SORT_BY_DATE:
                this.sortingStrategy = new SortByDateStrategy();
                break;
            case SORT_BY_LOWEST_PRICE:
                this.sortingStrategy = new SortByLowestPrice();
                break;
            case SORT_BY_HIGHEST_PRICE:
                this.sortingStrategy = new SortByHighestPrice();
                break;

        }

        this.displayJobsList = this.sortingStrategy.sort(this.jobsList);

        mAdapter.updateContents(this.displayJobsList);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * Manditory override: Called when nothing is selected on the spinner dropdown
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // do nothing
    }
}
