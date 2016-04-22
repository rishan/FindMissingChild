package com.beehyv.findmissingchild.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.beehyv.findmissingchild.R;
import com.beehyv.findmissingchild.adapters.ImageAdapter;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 100;

    private ImageView imageView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.imageView = (ImageView)this.findViewById(R.id.imageView1);
        Button photoButton = (Button) this.findViewById(R.id.open_camera);
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent cameraIntent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        RecyclerView recyclerView= (RecyclerView)findViewById(R.id.images);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        ImageAdapter imageAdapter=new ImageAdapter(createDummyImageList());
        recyclerView.setAdapter(imageAdapter);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
    }

    private ArrayList<Bitmap> createDummyImageList(){
        ArrayList<Bitmap> images=new ArrayList<>();
        images.add(BitmapFactory.decodeResource(getResources(), R.drawable.splash));
        images.add(BitmapFactory.decodeResource(getResources(), R.drawable.splash));
        images.add(BitmapFactory.decodeResource(getResources(), R.drawable.splash));
        images.add(BitmapFactory.decodeResource(getResources(), R.drawable.splash));
        images.add(BitmapFactory.decodeResource(getResources(), R.drawable.splash));
        return images;
    }
}