package com.example.omarelrayes.dos.model;

import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by Omar ELRayes on 3/21/2018.
 */

public class MonumentRepository {
    private static MonumentRepository INSTANCE;
    private final MonumentDAO monumentDAO;

    private MonumentRepository(MonumentDAO monumentDAO) {
        this.monumentDAO = monumentDAO;
    }

    public static MonumentRepository getINSTANCE(MonumentDAO dao) {
        if (INSTANCE == null) {
            INSTANCE =
                    new MonumentRepository(dao);
        }
        return INSTANCE;
    }

    public LiveData<List<Monument>> getMonumetns() {
        return monumentDAO.getMonuments();
    }

    public LiveData<Monument> getMonumentById(int id) {
        return monumentDAO.getMonumentById(id);
    }

    public void deleteMonument(Monument monument) {
        monumentDAO.deleteMonument(monument);
    }

    public void insertMonument(Monument monument) {
        monumentDAO.insertMonument(monument);
    }

    public void nukeDataBase() {
        monumentDAO.nukeDataBase();
    }
}
