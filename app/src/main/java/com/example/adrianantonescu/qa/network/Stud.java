package com.example.adrianantonescu.qa.network;

import java.io.Serializable;

public class Stud implements Serializable {
    private String fname;
    private String lname;
    private Float result;

    public Stud() {
    }

    public Stud(String fname, String lname, Float result) {
        this.fname = fname;
        this.lname = lname;
        this.result = result;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Float getResult() {
        return result;
    }

    public void setResult(Float result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return this.lname+" "+this.fname+"    -   "+this.result.toString()+"\n";
    }
}
