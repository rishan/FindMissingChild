package com.beehyv.findmissingchild.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.beehyv.findmissingchild.R;
import com.beehyv.findmissingchild.pojos.ChildDetails;

import java.util.List;

/**
 * Created by rishan on 28/4/16.
 */
public class ChildrenAdapter extends RecyclerView.Adapter<ChildrenAdapter.CustomChildrenViewHolder>{

    List<ChildDetails> childrenList;
    TextView childName,childAge,ChildMissingFrom;

    public ChildrenAdapter(List<ChildDetails> childrenList) {
        this.childrenList=childrenList;
    }

    @Override
    public CustomChildrenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.added_child_details_layout, null);
        return new CustomChildrenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomChildrenViewHolder holder, int position) {
        ChildDetails currentChildDetails=childrenList.get(position);
        holder.childName.setText(currentChildDetails.getName());
        holder.childAge.setText(currentChildDetails.getAgeRange());
        holder.childMissingFrom.setText(currentChildDetails.getMissingFrom());
        holder.childImage.setImageBitmap(currentChildDetails.getImages()[0]);
    }

    @Override
    public int getItemCount() {
        return childrenList.size();
    }

    public class CustomChildrenViewHolder extends RecyclerView.ViewHolder{
        private TextView childName,childAge,childMissingFrom;
        private ImageView childImage;
        public CustomChildrenViewHolder(View itemView) {
            super(itemView);
            this.childName=(TextView) itemView.findViewById(R.id.child_name);
            this.childAge=(TextView) itemView.findViewById(R.id.child_age);
            this.childMissingFrom=(TextView) itemView.findViewById(R.id.child_missing_from);
            this.childImage=(ImageView) itemView.findViewById(R.id.child_image);
        }
    }
}
