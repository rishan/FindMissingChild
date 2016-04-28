package com.beehyv.findmissingchild.adapters;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.beehyv.findmissingchild.R;
import com.beehyv.findmissingchild.activities.AddedChildren;
import com.beehyv.findmissingchild.activities.MainActivity;
import com.beehyv.findmissingchild.activities.PagerActivity;

/**
 * Created by rishan on 26/4/16.
 */
public class DrawerItemClickListener implements ListView.OnItemClickListener {

    Intent intent;
    Context context;
    public DrawerItemClickListener(Context context){
        this.context=context;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectItem(position);
    }

    private void selectItem(int position) {

       switch (position) {
            case 0:
                intent=new Intent(context,MainActivity.class);
                break;
            case 1:
                intent=new Intent(context,AddedChildren.class);
                break;
            case 2:
                //TODO "error in creating fragment"
                intent=new Intent(context,PagerActivity.class);
                break;
            default:
                break;
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }
}