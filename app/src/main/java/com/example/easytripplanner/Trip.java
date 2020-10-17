package com.example.easytripplanner;

import androidx.annotation.IdRes;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "trips_table")
public class Trip {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String userId;
    private String tripName;
    private String startPoint;
    private String endPoint;
    private long startDateinMillisec;
    private long startTimeinMillisec;
    private String notes;
    private String repeater;
    private String tripType;
    private String status;

    public Trip(String userId, String tripName, String startPoint, String endPoint, long startDateinMillisec, long startTimeinMillisec, String notes, String repeater, String tripType, String status) {
        this.userId = userId;
        this.tripName = tripName;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.startDateinMillisec = startDateinMillisec;
        this.startTimeinMillisec = startTimeinMillisec;
        this.notes = notes;
        this.repeater = repeater;
        this.tripType = tripType;
        this.status = status;
    }

    @Ignore
    public Trip(String tripName, String startPoint, String endPoint, long startDateinMillisec, long startTimeinMillisec, String status) {
        this.tripName = tripName;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.startDateinMillisec = startDateinMillisec;
        this.startTimeinMillisec = startTimeinMillisec;
        this.status = status;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public long getStartDateinMillisec() {
        return startDateinMillisec;
    }

    public void setStartDateinMillisec(long startDateinMillisec) {
        this.startDateinMillisec = startDateinMillisec;
    }

    public long getStartTimeinMillisec() {
        return startTimeinMillisec;
    }

    public void setStartTimeinMillisec(long startTimeinMillisec) {
        this.startTimeinMillisec = startTimeinMillisec;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getRepeater() {
        return repeater;
    }

    public void setRepeater(String repeater) {
        this.repeater = repeater;
    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }
}

