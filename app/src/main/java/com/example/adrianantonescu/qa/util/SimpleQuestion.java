package com.example.adrianantonescu.qa.util;

import java.util.ArrayList;

public class SimpleQuestion extends Question {

    private ArrayList<String> variante;
    private String variantaCorecta;

    public SimpleQuestion(int id, String subject, String questionText,
                          ArrayList variante, String variantaCorecta) {
        super(id, subject, questionText);
        this.variante = variante;
        this.variantaCorecta = variantaCorecta;
    }

    public ArrayList<String> getVariante() {
        return variante;
    }

    public void setVariante(ArrayList<String> variante) {
        this.variante = variante;
    }

    public String getVariantaCorecta() {
        return variantaCorecta;
    }

    public void setVariantaCorecta(String variantaCorecta) {
        this.variantaCorecta = variantaCorecta;
    }

    public void addVarianta(String var) {
        this.variante.add(var);
    }
}
