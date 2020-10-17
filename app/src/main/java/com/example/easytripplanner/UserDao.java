package com.example.easytripplanner;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Insert
    void addUser(User user);

    @Query("select * from User where uuid =:uid")
    User getUser(String uid);

    @Query("select * from User where uuid = :uid")
    LiveData<User> getUserLiveData(String uid);

    @Delete
    void  deletUser(User user);
}