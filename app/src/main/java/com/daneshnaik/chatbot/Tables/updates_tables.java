package com.daneshnaik.chatbot.Tables;

public class updates_tables {
String update_id;
String Title;
String Description;
String links;



    String anotherlink;
String date;




    public updates_tables(String update_id, String title, String description, String links, String date) {
        this.update_id = update_id;
        Title = title;
        Description = description;
        this.links = links;
        this.date = date;
    }







    public updates_tables() {

    }

    public updates_tables(String update_id, String title, String description, String links) {
        this.update_id = update_id;
        this.Title = title;
        this. Description = description;
        this.links = links;
    }

    public updates_tables(String update_id, String title, String description, String links, String anotherlink, String date) {
        this.update_id = update_id;
        Title = title;
        Description = description;
        this.links = links;
        this.anotherlink = anotherlink;
        this.date = date;
    }

    public updates_tables(String update_id, String title, String description) {
        this.update_id = update_id;
        Title = title;
        Description = description;
    }


    public String getUpdate_id() {
        return update_id;
    }

    public void setUpdate_id(String update_id) {
        this.update_id = update_id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getAnotherlink() {
        return anotherlink;
    }

    public void setAnotherlink(String anotherlink) {
        this.anotherlink = anotherlink;
    }
}
