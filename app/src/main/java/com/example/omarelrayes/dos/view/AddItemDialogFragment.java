package com.example.omarelrayes.dos.view;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.omarelrayes.dos.R;
import com.example.omarelrayes.dos.model.Monument;
import com.example.omarelrayes.dos.model.MonumentDAO;
import com.example.omarelrayes.dos.model.MonumentDataBase;
import com.example.omarelrayes.dos.model.MonumentRepository;
import com.example.omarelrayes.dos.viewmodel.AddMonumentViewModel;
import com.example.omarelrayes.dos.viewmodel.CustomViewModelFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Omar ELRayes on 3/21/2018.
 */

public class AddItemDialogFragment extends DialogFragment {
    @BindView(R.id.title)
    EditText title;
    @BindView(R.id.desc)
    EditText desc;
    @BindView(R.id.img)
    CircleImageView img;
    @BindView(R.id.add_btn)
    Button add;

    ViewModelProvider.Factory viewModelFactory;
    MonumentDAO monumentDAO;
    MonumentDataBase monumentDataBase;
    MonumentRepository repository;
    AddMonumentViewModel addMonumentViewModel;

    Bitmap bitmap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.add_item_dialog, container, false);
        ButterKnife.bind(this, root);
        init();
        addMonumentViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(AddMonumentViewModel.class);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Item Added", Toast.LENGTH_SHORT).show();
                if (title.getText().toString().isEmpty()
                        || desc.getText().toString().isEmpty()
                        || bitmap == null) {
                    Toast.makeText(getContext(), "Please Fill The Required Info", Toast.LENGTH_SHORT).show();
                } else {
                    Monument monument = new Monument(title.getText().toString(), desc.getText().toString(), getBytesFromBitmap(bitmap));
                    addMonumentViewModel.AddMonument(monument);
                    dismiss();
                }
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGallery();
            }
        });
        return root;
    }

    public void init() {
        monumentDataBase = MonumentDataBase.getINSTANCE(getContext());
        monumentDAO = monumentDataBase.MonumentDAO();
        repository = MonumentRepository.getINSTANCE(monumentDAO);
        viewModelFactory = CustomViewModelFactory.getINSTANCE(repository);
    }

    public byte[] getBytesFromBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            return stream.toByteArray();
        }
        return null;
    }

    private void startGallery() {
        Intent cameraIntent = new Intent(Intent.ACTION_GET_CONTENT);
        cameraIntent.setType("image/*");
        if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(cameraIntent, 1000);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super method removed
        if (resultCode == RESULT_OK) {
            if (requestCode == 1000) {
                Uri returnUri = data.getData();
                Bitmap bitmapImage = null;
                try {
                    bitmapImage = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), returnUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                bitmap = bitmapImage;
                img.setImageBitmap(bitmapImage);
            }
        }
    }
}
