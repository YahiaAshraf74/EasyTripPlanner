package com.example.easytripplanner;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Trip.class, version = 2)
public abstract class TripsDatabase extends RoomDatabase {
    private static TripsDatabase instance;

    public abstract TripsDao tripsDao();

    public static synchronized TripsDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), TripsDatabase.class, "trips_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
