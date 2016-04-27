package com.beehyv.findmissingchild.pojos;

/**
 * Created by rishan on 27/4/16.
 */
public class UserData {
    private String name;
    private int phoneNumber;
    private String emailID;

    public UserData(String name, int phoneNumber, String emailID) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailID = emailID;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

}
