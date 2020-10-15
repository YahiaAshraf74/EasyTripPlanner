package com.example.easytripplanner;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class UpComingFragment extends Fragment {

    RecyclerView recyclerViewUpcoming;
    TripAdapter tripAdapter;
    ArrayList<Trip> tripArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_up_coming, container, false);
        recyclerViewUpcoming = view.findViewById(R.id.recyclerview_upcoming);

        tripArrayList = new ArrayList<>();
  /*
 there is error in date and time
        tripArrayList.add(new Trip("visit", "Alex", "Cairo", "12/2/2020", 330, "upcoming"));
        tripArrayList.add(new Trip("visit", "Alex", "Cairo", 12/2/2020, 330, "upcoming"));
        tripArrayList.add(new Trip("visit", "Alex", "Cairo", 12/2/2020, 330, "upcoming"));
        tripArrayList.add(new Trip("visit", "Alex", "Cairo", 12/2/2020, 330, "upcoming"));
        tripArrayList.add(new Trip("visit", "Alex", "Cairo", 12/2/2020, 330, "upcoming"));
        tripArrayList.add(new Trip("visit", "Alex", "Cairo", 12/2/2020, 330, "upcoming"));
        tripArrayList.add(new Trip("visit", "Alex", "Cairo", 12/2/2020, 330, "upcoming"));
        tripArrayList.add(new Trip("visit", "Alex", "Cairo", 12/2/2020, 330, "upcoming"));
*/
        tripAdapter = new TripAdapter(this,tripArrayList);
        recyclerViewUpcoming.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewUpcoming.setAdapter(tripAdapter);



        return view;
    }
}