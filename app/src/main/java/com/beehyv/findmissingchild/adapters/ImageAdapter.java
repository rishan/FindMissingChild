package com.beehyv.findmissingchild.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.beehyv.findmissingchild.R;
import com.beehyv.findmissingchild.activities.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rishan on 21/4/16.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.CustomImageViewHolder> {

    private List<Bitmap> clickedImages=new ArrayList<>();
    private interactionInterface listener;
    public final static int IMAGE_DISPLAY_VIEWTYPE=0;
    public final static int ADD_IMAGE_VIEWTYPE=1;

    public ImageAdapter(ArrayList<Bitmap> clickedImages, interactionInterface listener){
        this.clickedImages=clickedImages;
        this.listener = listener;
    }

    @Override
    public CustomImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(viewType==ADD_IMAGE_VIEWTYPE)
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_image, null);
        else
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_view, null);
        return new CustomImageViewHolder(view,viewType);

    }

    @Override
    public void onBindViewHolder(CustomImageViewHolder holder, int position) {
        if(getItemViewType(position)!=ADD_IMAGE_VIEWTYPE)
            holder.singleImage.setImageBitmap(clickedImages.get(position));
        else {
            holder.takePic.setImageResource(R.drawable.take_pic);

            holder.takePic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.loadCamera();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return clickedImages.size()+1;
    }
    @Override
    public int getItemViewType(int position) {
        if(position==getItemCount()-1)
            return ADD_IMAGE_VIEWTYPE;
        else
            return IMAGE_DISPLAY_VIEWTYPE;
    }

    public class CustomImageViewHolder extends RecyclerView.ViewHolder{
        ImageView singleImage;
        ImageView takePic;
        public CustomImageViewHolder(View itemView,int viewType) {
            super(itemView);
            this.singleImage=(ImageView)itemView.findViewById(R.id.singleImage);
            if(viewType==ADD_IMAGE_VIEWTYPE)
                this.takePic=(ImageView)itemView.findViewById(R.id.take_pic);
        }
    }


    public interface interactionInterface {
        void loadCamera();
    }
}
