package com.daneshnaik.chatbot.Tables;

public class materials {
    int id;
    String pdfname;

    public String getPdfurl() {
        return pdfurl;
    }

    public void setPdfurl(String pdfurl) {
        this.pdfurl = pdfurl;
    }

    String pdfurl;

    public materials(String pdfname,String pdfurl) {
        this.pdfname = pdfname;
        this.pdfurl=pdfurl;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPdfname() {
        return pdfname;
    }

    public void setPdfname(String pdfname) {
        this.pdfname = pdfname;
    }

    public materials(int id, String pdfname) {
        this.id = id;
        this.pdfname = pdfname;
    }
}
