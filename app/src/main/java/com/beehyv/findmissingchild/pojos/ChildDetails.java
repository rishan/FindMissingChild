package com.beehyv.findmissingchild.pojos;

import android.graphics.Bitmap;

/**
 * Created by rishan on 27/4/16.
 */
public class ChildDetails {

    private String location;
    private String name;
    private String ageRange;
    //gender=true ---> Male
    private boolean gender;
    private String missingFrom;
    private Bitmap[] images;

    public ChildDetails(String location, String name, String ageRange, boolean gender, String missingFrom, Bitmap[] images) {
        this.location = location;
        this.name = name;
        this.ageRange = ageRange;
        this.gender = gender;
        this.missingFrom = missingFrom;
        this.images = images;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getMissingFrom() {
        return missingFrom;
    }

    public void setMissingFrom(String missingFrom) {
        this.missingFrom = missingFrom;
    }

    public Bitmap[] getImages() {
        return images;
    }

    public void setImages(Bitmap[] images) {
        this.images = images;
    }

}
