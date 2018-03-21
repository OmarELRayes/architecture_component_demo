package com.example.omarelrayes.dos.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Omar ELRayes on 3/21/2018.
 */
@Dao
public interface MonumentDAO {

    @Query("SELECT * FROM Monument")
    LiveData<List<Monument>> getMonuments();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertMonument(Monument monument);

    @Query("SELECT * FROM Monument WHERE id = :id")
    LiveData<Monument> getMonumentById(int id);

    @Delete()
    void deleteMonument(Monument monument);

    @Query("DELETE  FROM Monument")
    void nukeDataBase();

}
