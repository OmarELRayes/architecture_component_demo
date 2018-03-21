package com.example.omarelrayes.dos.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.omarelrayes.dos.R;
import com.example.omarelrayes.dos.model.Monument;
import com.example.omarelrayes.dos.model.MonumentDAO;
import com.example.omarelrayes.dos.model.MonumentDataBase;
import com.example.omarelrayes.dos.model.MonumentRepository;
import com.example.omarelrayes.dos.viewmodel.CustomViewModelFactory;
import com.example.omarelrayes.dos.viewmodel.MonumentCollectionViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    List<Monument> monumentList;
    MonumentsAdapter adapter;

    ViewModelProvider.Factory viewModelFactory;
    MonumentDAO monumentDAO;
    MonumentDataBase monumentDataBase;
    MonumentRepository repository;
    MonumentCollectionViewModel monumentCollectionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        monumentList = new ArrayList<>();
        adapter = new MonumentsAdapter(monumentList, this);
        recyclerView.setAdapter(adapter);
        init();
        monumentCollectionViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MonumentCollectionViewModel.class);
        monumentCollectionViewModel.getMonumetns().observe(this, new Observer<List<Monument>>() {
            @Override
            public void onChanged(@Nullable List<Monument> monuments) {
                adapter.addMonuments(monuments);
                Toast.makeText(MainActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onChanged: " + monuments.size());
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                AddItemDialogFragment addItemDialogFragment = new AddItemDialogFragment();
                addItemDialogFragment.show(fm, "kappa");
                //monumentCollectionViewModel.nukeDataBase();
            }
        });
    }

    public void init() {
        monumentDataBase = MonumentDataBase.getINSTANCE(this);
        monumentDAO = monumentDataBase.MonumentDAO();
        repository = MonumentRepository.getINSTANCE(monumentDAO);
        viewModelFactory = CustomViewModelFactory.getINSTANCE(repository);
    }
}
