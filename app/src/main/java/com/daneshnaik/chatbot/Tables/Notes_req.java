package com.daneshnaik.chatbot.Tables;

public class Notes_req {
    String id,email,message;

    public Notes_req(String id, String email, String message) {
        this.id = id;
        this.email = email;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
