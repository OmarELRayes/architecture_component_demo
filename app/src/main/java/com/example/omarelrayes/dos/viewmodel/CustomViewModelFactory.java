package com.example.omarelrayes.dos.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.omarelrayes.dos.model.MonumentRepository;


/**
 * Created by Omar ELRayes on 3/21/2018.
 */

public class CustomViewModelFactory implements ViewModelProvider.Factory {
    private static CustomViewModelFactory INSTANCE;
    private MonumentRepository repository;

    private CustomViewModelFactory(MonumentRepository repository) {
        this.repository = repository;
    }

    public static CustomViewModelFactory getINSTANCE(MonumentRepository repository) {
        if (INSTANCE == null) {
            INSTANCE = new CustomViewModelFactory(repository);
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AddMonumentViewModel.class)) {
            return (T) new AddMonumentViewModel(repository);
        } else if (modelClass.isAssignableFrom(MonumentCollectionViewModel.class)) {
            return (T) new MonumentCollectionViewModel(repository);
        } else if (modelClass.isAssignableFrom(MonumentViewModel.class)) {
            return (T) new MonumentViewModel(repository);
        } else {
            throw new IllegalArgumentException("View Model Not Found");
        }
    }
}
