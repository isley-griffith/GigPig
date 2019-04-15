package com.example.gigpig;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Fragment to manage search view
 */
public class SearchFragment extends Fragment implements View.OnKeyListener {

    EditText search_bar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        search_bar = getView().findViewById(R.id.search_bar);
//        search_bar.setOnKeyListener(this);


        super.onViewCreated(view, savedInstanceState);
        
    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent event) {
        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                (keyCode == KeyEvent.KEYCODE_ENTER)) {
            // Perform action on key press

            this.search_bar.getText();

            return true;
        }
        return false;
    }
}

