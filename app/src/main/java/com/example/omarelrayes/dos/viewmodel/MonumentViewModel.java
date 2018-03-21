package com.example.omarelrayes.dos.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.omarelrayes.dos.model.Monument;
import com.example.omarelrayes.dos.model.MonumentRepository;

/**
 * Created by Omar ELRayes on 3/21/2018.
 */

public class MonumentViewModel extends ViewModel {

    private MonumentRepository repository;

    public MonumentViewModel(MonumentRepository repository) {
        this.repository = repository;
    }

    public LiveData<Monument> getMonumentById(int id) {
        return repository.getMonumentById(id);
    }
}
