package com.example.omarelrayes.dos.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Omar ELRayes on 3/21/2018.
 */
@Database(entities = {Monument.class}, version = 2)
public abstract class MonumentDataBase extends RoomDatabase {

    public static MonumentDataBase INSTANCE;

    public static MonumentDataBase getINSTANCE(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), MonumentDataBase.class, "monument_db").fallbackToDestructiveMigration()
                            .build();
        }
        return INSTANCE;

    }

    public abstract MonumentDAO MonumentDAO();
}
