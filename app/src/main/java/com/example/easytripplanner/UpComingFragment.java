package com.example.easytripplanner;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class UpComingFragment extends Fragment {

    RecyclerView recyclerViewUpcoming;
    TripAdapter tripAdapter;

    // !!!! this code used to get all trips
    private TripsDatabase tripsDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_up_coming, container, false);
        final String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.e("upcoming", userId);
        recyclerViewUpcoming = view.findViewById(R.id.recyclerview_upcoming);


        // !!!! this code used to get all trips
        tripsDatabase = TripsDatabase.getInstance(getContext());
        tripsDatabase.tripsDao().getAllTrip()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Trip>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Trip> trips) {
                        LinearLayoutManager llm = new LinearLayoutManager(getContext());
                        llm.setOrientation(LinearLayoutManager.VERTICAL);
                        Log.e("onSuccess", "" + trips.size());
                        tripAdapter = new TripAdapter(getContext(), trips);
                        recyclerViewUpcoming.setLayoutManager(llm);
                        recyclerViewUpcoming.setAdapter(tripAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
        return view;
    }
}