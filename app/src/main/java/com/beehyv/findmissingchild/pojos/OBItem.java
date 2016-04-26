package com.beehyv.findmissingchild.pojos;

import com.beehyv.findmissingchild.R;

/**
 * Created by rishan on 26/4/16.
 */
public class OBItem {
    private int obNumberImage;
    private int obImage;
    private String sectionText;

    public OBItem(int obNumberImage,int obImage, String sectionText){
        this.obImage=obImage;
        this.obNumberImage=obNumberImage;
        this.sectionText=sectionText;
    }

    public int getObNumberImage(){
        return obNumberImage;
    }
    public int getObImage(){
        return obImage;
    }
    public String getSectionText(){
        return sectionText;
    }

    public static OBItem OBObjectSender(int sectionNumber){
        if(sectionNumber==1)
            return new OBItem(R.drawable.ob1num,R.drawable.ob1image,"Whenever you see a child \nOpen the app and take the picture with\n a single click.");
        else if (sectionNumber==2)
            return new OBItem(R.drawable.ob2num,R.drawable.ob2image,"Open your App Camera and \ntake the pictures as quickly as\n possible.");
        else
            return new OBItem(R.drawable.ob3num,R.drawable.ob3image,"Fill up the details and \nsubmit the information and \ndone!");
    }
}
