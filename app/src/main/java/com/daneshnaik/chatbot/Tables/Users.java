package com.daneshnaik.chatbot.Tables;

public class Users {
    String id;
    String name;
    String phoneNumber,profileImage;
    String Email;

    String Lastmessage;


    public  Users(){

    }


    public Users(String id, String name, String phoneNumber, String profileImage, String email) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
        Email = email;
    }

    public Users(String id, String name, String phoneNumber, String profileImage, String email, String lastmessage) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
        Email = email;
        Lastmessage = lastmessage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getLastmessage() {
        return Lastmessage;
    }

    public void setLastmessage(String lastmessage) {
        Lastmessage = lastmessage;
    }
}
