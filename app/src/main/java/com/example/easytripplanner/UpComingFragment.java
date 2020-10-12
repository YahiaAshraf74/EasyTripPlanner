package com.example.easytripplanner;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class UpComingFragment extends Fragment {

    RecyclerView recyclerViewUpcoming;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_up_coming, container, false);
        recyclerViewUpcoming = view.findViewById(R.id.recyclerview_upcoming);
        return view;
    }
}