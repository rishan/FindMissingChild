package com.beehyv.findmissingchild.pojos;

/**
 * Created by rishan on 27/4/16.
 */
public class User {
    private String name;
    private long phoneNumber;
    private String emailID;
    private String uuid;

    public User() {

    }

    public User(String name, long phoneNumber, String emailID) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailID = emailID;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
