package com.example.easytripplanner;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.MyViewHolder> {

    Context context;
    ArrayList<Trip> trips;


    public TripAdapter(Context context, ArrayList<Trip> trips) {
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
        holder.date.setText((CharSequence) trip.getStartDate());
        holder.time.setText((CharSequence) trip.getStartTime());
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

                String name = trip.getTripName();
                String status = trip.getStatus();
                Date date = trip.getStartDate();
                Time time = trip.getStartTime();
                String startPoint = trip.getStartPoint();
                String endPoint = trip.getEndPoint();
                ArrayList<String> note = trip.getNotes();

                //delete MainActivity2 and put instead of it the name of the edit activity

          /*    Intent intent =new Intent(context,MainActivity2.class);
                intent.putExtra("Name", name);
                intent.putExtra("Status", status);
                intent.putExtra("Date", date);
                intent.putExtra("Time", time);
                intent.putExtra("StartPoint", startPoint);
                intent.putExtra("EndPoint", endPoint);
                intent.putExtra("Notes", note);
                context.startActivity(intent);
          */

                // in the edit activity you will write this code
          /*
                String name = getIntent().getStringExtra("Name");
                String status = getIntent().getStringExtra("Status");
                String date = getIntent().getStringExtra("Date");
                String time = getIntent().getStringExtra("Time");
                String startPoint = getIntent().getStringExtra("StartPoint");
                String endPoint = getIntent().getStringExtra("EndPoint");
                 ArrayList<String> note = getIntent().getStringArrayListExtra("Notes");

                // and in every edit text will write
                //edittext.setText("name one of the above variable");
         */
            }
        });

    }

    @Override
    public int getItemCount() {
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
            notes = itemView.findViewById(R.id.show_notes);
            start = itemView.findViewById(R.id.start_trip_customrow);
        }
    }
}

