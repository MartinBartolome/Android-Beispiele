package com.example.dbroomexample;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FahrerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Fahrer fahrer);

    @Query("DELETE FROM rangliste")
    void deleteAll();

    @Query("SELECT * from rangliste ORDER BY punkte DESC")
    LiveData<List<Fahrer>> getFahrer();
}
