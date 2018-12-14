package com.example.adrianantonescu.qa.util;

public class student extends user {
    private String nume;
    private String prenume;
    private String grupa;
    private String serie;
    private int an;
    private String specializare;
    private String email;


    public student(String username, String password, String nume, String prenume, String grupa, String serie, int an, String specializare, String email) {

        super(username, password);
        this.nume = nume;
        this.prenume = prenume;
        this.grupa = grupa;
        this.serie = serie;
        this.an = an;
        this.specializare = specializare;
        this.email = email;
    }

    @Override
    public String toString() {
        return "student{" +
                "nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", grupa='" + grupa + '\'' +
                ", serie='" + serie + '\'' +
                ", an=" + an +
                ", specializare='" + specializare + '\'' +
                ", email='" + email + '\'' +
                '}';
    }



    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getGrupa() {
        return grupa;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getAn() {
        return an;
    }

    public void setAn(int an) {
        this.an = an;
    }

    public String getSpecializare() {
        return specializare;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
