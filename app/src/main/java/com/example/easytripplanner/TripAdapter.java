package com.example.easytripplanner;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.MyViewHolder> {

    Context context;
    List<Trip> trips;
    public static final String activityName = "Activity Name";


    public TripAdapter(Context context, List<Trip> trips) {
        this.context = context;
        this.trips = trips;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_row, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Trip trip = trips.get(position);
        holder.date.setText(trip.miliSecToDate());
        holder.time.setText(trip.miliSecToTime());
        holder.name.setText(trip.getTripName());
        holder.status.setText(trip.getStatus());
        holder.startpoint.setText(trip.getStartPoint());
        holder.endpoint.setText(trip.getEndPoint());

        holder.start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code to make alert dialog and notification
            }
        });
        holder.notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code to show all notes the user enter it
            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, AddTrip.class);
                intent.putExtra(TripAdapter.activityName, "Switch");
                intent.putExtra("Name",  trip.getTripName());
                intent.putExtra("StartPoint", trip.getStartPoint());
                intent.putExtra("EndPoint", trip.getEndPoint());
                intent.putExtra("Date", trip.miliSecToDate());
                intent.putExtra("Time", trip.miliSecToTime());
                intent.putExtra("tripType", trip.getTripType());
                intent.putExtra("repeater", trip.getRepeater());
                intent.putExtra("Notes", trip.getNotes());
                intent.putExtra("Status", trip.getStatus());
                context.startActivity(intent);

            }
        });

    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    @Override
    public int getItemCount() {
        Log.e("adapter", trips.size() + "\n");
        if (trips == null)
            return 0;
        if (trips.isEmpty())
            return 0;
        return trips.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView date, time, name, status, startpoint, endpoint;
        ImageView notes;
        Button start;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.text1date);
            time = itemView.findViewById(R.id.text2time);
            name = itemView.findViewById(R.id.text3name);
            status = itemView.findViewById(R.id.text4status);
            startpoint = itemView.findViewById(R.id.text5startpoint);
            endpoint = itemView.findViewById(R.id.text6endpoint);
            //notes = itemView.findViewById(R.id.show_notes);
            start = itemView.findViewById(R.id.start_trip_customrow);
        }
    }
}

