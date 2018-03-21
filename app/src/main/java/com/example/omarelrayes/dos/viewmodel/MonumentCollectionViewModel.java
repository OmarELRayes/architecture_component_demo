package com.example.omarelrayes.dos.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.example.omarelrayes.dos.model.Monument;
import com.example.omarelrayes.dos.model.MonumentRepository;

import java.util.List;

/**
 * Created by Omar ELRayes on 3/21/2018.
 */

public class MonumentCollectionViewModel extends ViewModel {

    private MonumentRepository repository;

    public MonumentCollectionViewModel(MonumentRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Monument>> getMonumetns() {
        return repository.getMonumetns();
    }

    public void nukeDataBase() {
        new nukeTask().execute();
    }

    public class nukeTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            repository.nukeDataBase();
            return null;
        }
    }
}
