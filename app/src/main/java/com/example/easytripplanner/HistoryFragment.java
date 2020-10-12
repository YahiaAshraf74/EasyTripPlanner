package com.example.easytripplanner;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class HistoryFragment extends Fragment {
    RecyclerView recyclerViewHistory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_history, container, false);
        recyclerViewHistory = view.findViewById(R.id.recyclerview_history);
        return view;
    }
}