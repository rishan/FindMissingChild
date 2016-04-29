package com.beehyv.findmissingchild.pojos;

import android.graphics.Bitmap;

/**
 * Created by rishan on 27/4/16.
 */
public class ChildDetails {

    private String locationFound;
    private String name;
    private RangeChoices ageRange;
    //gender=true ---> Male
    private boolean gender;
    private String missingFrom;
    private Bitmap[] images;

    public ChildDetails() {}

    public ChildDetails(String location, String name, RangeChoices ageRange, boolean gender, String missingFrom, Bitmap[] images) {
        this.locationFound = location;
        this.name = name;
        this.ageRange = ageRange;
        this.gender = gender;
        this.missingFrom = missingFrom;
        this.images = images;
    }

    public String getLocationFound() {
        return locationFound;
    }

    public void setLocationFound(String location) {
        this.locationFound = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RangeChoices getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(RangeChoices ageRange) {
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

enum RangeChoices {

    ZERO_TO_FIVE(0),
    FIVE_TO_TEN(1),
    TEN_TO_FIFTEEN(2),
    FIFTEEN_TO_TWENTY(3);

    private final int id;

    RangeChoices(int id) {
        this.id = id;
    }

    public int getValue() {
        return id;
    }
}