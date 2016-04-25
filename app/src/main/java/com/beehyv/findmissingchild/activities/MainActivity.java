package com.beehyv.findmissingchild.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.beehyv.findmissingchild.R;
import com.beehyv.findmissingchild.adapters.ImageAdapter;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements ImageAdapter.interactionInterface {
    private static final int CAMERA_REQUEST = 100;
    private RecyclerView recyclerView;
    private ArrayList<Bitmap> imageList=new ArrayList<>();
    private ImageAdapter imageAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.childDetailsPageTitle);
        imageList=new ArrayList<>();
        if(imageList.isEmpty())
            loadCamera();

            recyclerView = (RecyclerView) findViewById(R.id.images);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);
            imageAdapter = new ImageAdapter(imageList, this);
            recyclerView.setAdapter(imageAdapter);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageList.add(photo);
            imageAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void loadCamera(){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }
}