package com.example.adrianantonescu.qa.util;

public class Feedback {
    private int id;
    private String nume;
    private String mesaj;

    public Feedback(int id, String nume, String mesaj) {
        this.id = id;
        this.nume = nume;
        this.mesaj = mesaj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }
}
