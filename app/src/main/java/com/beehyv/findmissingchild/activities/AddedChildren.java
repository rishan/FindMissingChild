package com.beehyv.findmissingchild.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.beehyv.findmissingchild.R;
import com.beehyv.findmissingchild.adapters.ChildrenAdapter;
import com.beehyv.findmissingchild.pojos.ChildDetails;

import java.util.ArrayList;
import java.util.List;

public class AddedChildren extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_added_children);
        setTitle("Added Child List");

        List<ChildDetails> childrenList= (List<ChildDetails>) getIntent().getExtras().get("childrenList");

        RecyclerView childRecyclerView=(RecyclerView)findViewById(R.id.childRecyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        childRecyclerView.setLayoutManager(linearLayoutManager);
        ChildrenAdapter childrenAdapter=new ChildrenAdapter(childrenList);
        childRecyclerView.setAdapter(childrenAdapter);
    }
}
