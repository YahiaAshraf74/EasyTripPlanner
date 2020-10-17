package com.example.easytripplanner;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface TripsDao {
    @Insert
    Completable insertTrip(Trip trip);
//    void insertTrip(Trip trip);

    @Query("select * from trips_table where userId=:uID")
    Single<List<Trip>> getTrip(String uID);

    @Query("select * from trips_table")
    Single<List<Trip>> getAllTrip();
//    ArrayList<Trip> getTrip();
}
