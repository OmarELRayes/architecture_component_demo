package com.example.omarelrayes.dos.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omarelrayes.dos.R;
import com.example.omarelrayes.dos.model.Monument;
import com.example.omarelrayes.dos.model.MonumentDAO;
import com.example.omarelrayes.dos.model.MonumentDataBase;
import com.example.omarelrayes.dos.model.MonumentRepository;
import com.example.omarelrayes.dos.viewmodel.CustomViewModelFactory;
import com.example.omarelrayes.dos.viewmodel.MonumentViewModel;

import java.io.ByteArrayInputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.details_title)
    TextView title;

    @BindView(R.id.details_desc)
    TextView desc;

    @BindView(R.id.details_img)
    ImageView img;


    ViewModelProvider.Factory viewModelFactory;
    MonumentDAO monumentDAO;
    MonumentDataBase monumentDataBase;
    MonumentRepository repository;
    MonumentViewModel monumentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        Intent i = getIntent();
        int id = i.getIntExtra("itemId", 0);
        Toast.makeText(this, "item id = " + id, Toast.LENGTH_SHORT).show();
        init();
        monumentViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MonumentViewModel.class);

        monumentViewModel.getMonumentById(id).observe(this, new Observer<Monument>() {
            @Override
            public void onChanged(@Nullable Monument listItem) {
                if (listItem != null) {
                    title.setText(listItem.getTitle());
                    desc.setText(listItem.getDesc());
                    img.setImageBitmap(ByteArrayToBitmap(listItem.getImage()));
                }

            }
        });
    }

    public void init() {
        monumentDataBase = MonumentDataBase.getINSTANCE(this);
        monumentDAO = monumentDataBase.MonumentDAO();
        repository = MonumentRepository.getINSTANCE(monumentDAO);
        viewModelFactory = CustomViewModelFactory.getINSTANCE(repository);
    }

    public Bitmap ByteArrayToBitmap(byte[] byteArray) {
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(byteArray);
        Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);
        return bitmap;
    }
}
