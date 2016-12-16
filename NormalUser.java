package com.example.mohamedelhefnawy.socialnetwork;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by MohamedElHefnawy on 12/11/2016.
 */


public class NormalUser implements Serializable {
    private String ID;
    private String mail;
    private Date birthDate;
    private String password;
    private String [] placeslist;

    public  void initialize(NormalUser user){
        user.setID(null);
        user.setBirthDate(null);
        user.setMail(null);
        user.setPassword(null);
        user.setPlaceslist(null);
    }
    public NormalUser(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }


    public void setID(String ID) {
        this.ID = ID;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPlaceslist(String[] placeslist) {
        this.placeslist = placeslist;
    }

    public String getID() {
        return ID;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String[] getPlaceslist() {
        return placeslist;
    }


}
