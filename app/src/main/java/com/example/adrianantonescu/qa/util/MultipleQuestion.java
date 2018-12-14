package com.example.adrianantonescu.qa.util;

import java.util.ArrayList;

public class MultipleQuestion extends Question {

    private ArrayList<String> variante = new ArrayList<>();
    private ArrayList<String> varianteCorecte;

    public MultipleQuestion(int id, String subject, String questionText,
                            ArrayList<String> variante, ArrayList<String> varianteCorecte) {
        super(id, subject, questionText);
        this.variante = variante;
        this.varianteCorecte = varianteCorecte;
    }

    public ArrayList<String> getVariante() {
        return variante;
    }

    public void setVariante(ArrayList<String> variante) {
        this.variante = variante;
    }

    public ArrayList<String> getVarianteCorecte() {
        return varianteCorecte;
    }

    public void setVarianteCorecte(ArrayList<String> varianteCorecte) {
        this.varianteCorecte = varianteCorecte;
    }

    public void addVarianta(String var) {
        this.variante.add(var);
    }

    public void addVariantaCorecta(String var) {
        this.varianteCorecte.add(var);
    }
}
