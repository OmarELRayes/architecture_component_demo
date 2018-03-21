package com.example.omarelrayes.dos.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.example.omarelrayes.dos.model.Monument;
import com.example.omarelrayes.dos.model.MonumentRepository;

/**
 * Created by Omar ELRayes on 3/21/2018.
 */

public class AddMonumentViewModel extends ViewModel {

    private MonumentRepository repository;

    public AddMonumentViewModel(MonumentRepository repository) {
        this.repository = repository;
    }

    public void AddMonument(Monument monument) {
        new AddMonumentTask().execute(monument);
    }

    private class AddMonumentTask extends AsyncTask<Monument, Void, Void> {

        @Override
        protected Void doInBackground(Monument... monuments) {
            repository.insertMonument(monuments[0]);
            return null;
        }
    }
}
