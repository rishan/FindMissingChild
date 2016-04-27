package com.beehyv.findmissingchild.pojos;

import com.beehyv.findmissingchild.R;

/**
 * Created by rishan on 26/4/16.
 */
public class OBItem {
    private int obNumberImage;
    private int obImage;
    private int sectionText;

    public OBItem(int obNumberImage,int obImage, int sectionText){
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
    public int getSectionText(){
        return sectionText;
    }

    public static OBItem OBObjectSender(int sectionNumber){
        if(sectionNumber==1)
            return new OBItem(R.drawable.ob1num,R.drawable.ob1image,R.string.ob1text);
        else if (sectionNumber==2)
            return new OBItem(R.drawable.ob2num,R.drawable.ob2image,R.string.ob2text);
        else
            return new OBItem(R.drawable.ob3num,R.drawable.ob3image,R.string.ob3text);
    }
}
