package com.beehyv.findmissingchild.adapters;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.beehyv.findmissingchild.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rishan on 21/4/16.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.CustomImageViewHolder> {

    List<Bitmap> clickedImages=new ArrayList<>();

    public ImageAdapter(ArrayList<Bitmap> clickedImages){
        this.clickedImages=clickedImages;
    }

    @Override
    public CustomImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.image_view, null);
        return new CustomImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomImageViewHolder holder, int position) {
        holder.singleImage.setImageBitmap(clickedImages.get(position));
    }

    @Override
    public int getItemCount() {
        return clickedImages.size();
    }

    public class CustomImageViewHolder extends RecyclerView.ViewHolder{
        ImageView singleImage;
        public CustomImageViewHolder(View itemView) {
            super(itemView);
            this.singleImage=(ImageView)itemView.findViewById(R.id.singleImage);
        }
    }
}
