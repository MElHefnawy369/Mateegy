package com.example.mohamedelhefnawy.socialnetwork_2;

/**
 * Created by LENOVO on 12/22/2016.
 */

public class UserData {
    private String name;
    private String mail;
    private String ID;
    UserData(String namee, String maill, String idd){
        setName(namee);
        setMail(maill);
        setID(idd);
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
