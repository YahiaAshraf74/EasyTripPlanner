package com.example.easytripplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddTrip extends AppCompatActivity {
    public static final String API_KEY = "AIzaSyBq3hu0_i2dFOSs6yH8EluKgOjClYBJRcg";
    private EditText tripName, startPoint, endPoint, startDate, startTime, addNoteEditText;
    private TextView notesTextView;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private int hr, min;
    private Spinner repeater, tripType;
    private Button addNoteButton, backButton, AddTripButton, EditButton, cancelButton, deleteButton;
    private String nameTrip, startPointTrip, endPointTrip, repeaterTrip, tripTypeTrip, notesTrip, statusTrip;
    private String userId;
    long startDateTripinMillisec, startTimeTripinMillisec;
    private FirebaseAuth mAuth;
    private TripsDatabase tripsDatabase;
    public static String activityType;
    private static String currentNotes = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);
        mAuth = FirebaseAuth.getInstance();

        // get our database instance from room
        tripsDatabase = TripsDatabase.getInstance(this);

        tripName = findViewById(R.id.tripname_id);
        startPoint = findViewById(R.id.startpoint_id);
        endPoint = findViewById(R.id.endpoint_id);
        startDate = findViewById(R.id.startdate_id);
        startTime = findViewById(R.id.starttime_id);
        repeater = findViewById(R.id.repeater_id);
        tripType = findViewById(R.id.triptype_id);
        addNoteEditText = findViewById(R.id.txtaddnote_id);
        addNoteButton = findViewById(R.id.btnaddnote_id);
        notesTextView = findViewById(R.id.notes_id);
        backButton = findViewById(R.id.btnback);
        AddTripButton = findViewById(R.id.btn_addTrip);
        EditButton = findViewById(R.id.btnEdit);
        cancelButton = findViewById(R.id.btncanceltrip);
        deleteButton = findViewById(R.id.btndeletetrip);

        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notesTextView.setText(notesTextView.getText().toString() + "\n" + addNoteEditText.getText().toString());
                addNoteEditText.setText(null);
            }
        });

        // to add items to repeater spinner.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(AddTrip.this, R.array.itemRepeater, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        repeater.setAdapter(adapter);

        // to add items to tripType spinner.
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(AddTrip.this, R.array.itemTripType, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tripType.setAdapter(adapter1);

        // to switch add activity to edit&cancel Activity.
        Intent intent = getIntent();
        activityType = intent.getStringExtra(TripAdapter.activityName);

        if(activityType.equals("Switch"))
        {
            AddTripButton.setVisibility(View.INVISIBLE);
            EditButton.setVisibility(View.VISIBLE);
            cancelButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.VISIBLE);

            tripName.setText(getIntent().getStringExtra("Name"));
            startPoint.setText(getIntent().getStringExtra("StartPoint"));
            endPoint.setText(getIntent().getStringExtra("EndPoint"));
            startDate.setText(getIntent().getStringExtra("Date"));
            startTime.setText(getIntent().getStringExtra("Time"));
            repeater.setSelection(adapter.getPosition(getIntent().getStringExtra("repeater")));
            tripType.setSelection(adapter1.getPosition(getIntent().getStringExtra("tripType")));
            notesTextView.setText(getIntent().getStringExtra("Notes"));
        }

        // to don't let the editTexts force me to double click on them to popup .
        startPoint.setFocusable(false);
        endPoint.setFocusable(false);
        startDate.setFocusable(false);
        startTime.setFocusable(false);
        // Initialize places.
        Places.initialize(AddTrip.this, API_KEY);
        // to create intent that you can search for location (when u click on startPoint EditText).
        startPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initialize place fields.
                List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS, Place.Field.LAT_LNG, Place.Field.NAME);
                // the creation of the intent.
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fieldList).build(AddTrip.this);
                // start Activity result.
                startActivityForResult(intent, 100);
            }
        });

        // to create intent that you can search for location (when u click on Endpoint EditText).
        endPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initialize place fields.
                List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS, Place.Field.LAT_LNG, Place.Field.NAME);
                // the creation of the intent.
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fieldList).build(AddTrip.this);
                // start Activity result
                startActivityForResult(intent, 100);
            }
        });

        final Calendar calendar2 = Calendar.getInstance();

        // to popup datePicker.
        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);

                datePickerDialog = new DatePickerDialog(AddTrip.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        calendar2.set(Calendar.MONTH, month);
                        calendar2.set(Calendar.YEAR, year);
                        startDate.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }
                }, year, month, day);

                datePickerDialog.show();
            }
        });

        // to popup timePicker.
        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timePickerDialog = new TimePickerDialog(AddTrip.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar2.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar2.set(Calendar.MINUTE, minute);
                        calendar2.set(Calendar.SECOND, 0);
                        calendar2.set(Calendar.MILLISECOND, 0);
                        startTimeTripinMillisec = calendar2.getTimeInMillis();
                        startDateTripinMillisec = startTimeTripinMillisec;
                        Calendar calendar = Calendar.getInstance();
                        //initialize hour and minute.
                        hr = hourOfDay;
                        min = minute;
                        // initialize calendar.
                        // set hour snd minute.
                        calendar.set(0, 0, 0, hr, min);
                        // set selected time on editText
                        android.text.format.DateFormat df = new android.text.format.DateFormat();

                        startTime.setText(df.format("hh:mm aa", calendar));
                    }
                }, 12, 0, true);

                timePickerDialog.show();
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        AddTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nameTrip = tripName.getText().toString();
                startPointTrip = startPoint.getText().toString();
                endPointTrip = endPoint.getText().toString();
                repeaterTrip = getRepeater();
                tripTypeTrip = getTripType();
                Calendar calendar = Calendar.getInstance();
                notesTrip = notesTextView.getText().toString();
                statusTrip = (calendar.getTimeInMillis() < startTimeTripinMillisec) ? "Upcoming" : "Done";
                userId = mAuth.getCurrentUser().getUid();
                Log.e("addtrip", userId);

                Trip newTrip = new Trip(userId, nameTrip, startPointTrip, endPointTrip, startDateTripinMillisec, startTimeTripinMillisec, notesTrip, repeaterTrip, tripTypeTrip, statusTrip);
                tripsDatabase.tripsDao().insertTrip(newTrip)
                        .subscribeOn(Schedulers.computation())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onComplete() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        });
                startActivity(new Intent(AddTrip.this, HomeActivity.class));
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // update trip's status -----> cancel
            }
        });

        EditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // update all trip variables.
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // delete this trip
            }
        });

    }

    // return selected item in repeater spinner.
    public String getRepeater() {
        return repeater.getSelectedItem().toString();
    }

    // return selected item in tripType spinner.
    public String getTripType() {
        return tripType.getSelectedItem().toString();
    }

}