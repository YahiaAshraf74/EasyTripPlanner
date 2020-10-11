package com.example.easytripplanner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class AddTrip extends AppCompatActivity
{
    public static final String API_KEY = "AIzaSyBq3hu0_i2dFOSs6yH8EluKgOjClYBJRcg";
    EditText tripName;
    EditText startPoint;
    EditText endPoint;
    EditText startDate;
    DatePickerDialog datePickerDialog;
    EditText startTime;
    TimePickerDialog timePickerDialog;
    int hr, min;
    Spinner repeater;
    Spinner tripType;
    EditText addNote_txt;
    Button addNote_btn;
    ListView notes;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;
    Button btnCancel;
    Button btnAddTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);

        tripName = findViewById(R.id.tripname_id);
        startPoint = findViewById(R.id.startpoint_id);
        endPoint = findViewById(R.id.endpoint_id);
        startDate = findViewById(R.id.startdate_id);
        startTime = findViewById(R.id.starttime_id);
        repeater = findViewById(R.id.repeater_id);
        tripType = findViewById(R.id.triptype_id);
        addNote_txt = findViewById(R.id.txtaddnote_id);
        addNote_btn = findViewById(R.id.btnaddnote_id);
        notes = findViewById(R.id.notes_id);
        btnCancel = findViewById(R.id.btn_CancelActivity);
        btnAddTrip = findViewById(R.id.btn_addTrip);

        // set adapter to listView.
        String[] items = {};
        arrayList = new ArrayList<>(Arrays.asList(items));
        arrayAdapter = new ArrayAdapter<String>(AddTrip.this, R.layout.list_item, R.id.txtItem, arrayList);
        notes.setAdapter(arrayAdapter);

        // to Add Items to listView.
        addNote_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItem = addNote_txt.getText().toString();
                arrayList.add(newItem);
                arrayAdapter.notifyDataSetChanged();
                addNote_txt.setText(null);
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
                List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS
                        , Place.Field.LAT_LNG, Place.Field.NAME);

                // the creation of the intent.
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY , fieldList).build(AddTrip.this);

                // start Activity result.
                startActivityForResult(intent, 100);
            }
        });

        // to create intent that you can search for location (when u click on Endpoint EditText).
        endPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Initialize place fields.
                List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS
                        , Place.Field.LAT_LNG, Place.Field.NAME);

                // the creation of the intent.
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY , fieldList).build(AddTrip.this);

                // start Activity result
                startActivityForResult(intent, 100);

            }
        });


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
                        startDate.setText(dayOfMonth + "-" + (month+1) + "-" + year);
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
                        //initialize hour and minute.
                        hr = hourOfDay;
                        min = minute;
                        // initialize calendar.
                        Calendar calendar = Calendar.getInstance();
                        // set hour snd minute.
                        calendar.set(0, 0, 0, hr, min);
                        // set selected time on editText
                        android.text.format.DateFormat df = new android.text.format.DateFormat();
                        startTime.setText(df.format("hh:mm aa", calendar));
                    }
                },12, 0, true);

                timePickerDialog.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAddTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validation


                //write ur database code here.
            }
        });

    }

    // return selected item in repeater spinner.
    public String getRepeater()
    {
        return repeater.getSelectedItem().toString();
    }

    // return selected item in tripType spinner.
    public String getTripType()
    {
        return tripType.getSelectedItem().toString();
    }

}