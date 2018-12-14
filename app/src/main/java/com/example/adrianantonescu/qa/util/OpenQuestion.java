package com.example.adrianantonescu.qa.util;

public class OpenQuestion extends Question {

    private String questionAnswer;

    public OpenQuestion(int id, String materie, String questionText, String questionAnswer) {
        super(id, materie, questionText);
        this.questionAnswer = questionAnswer;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }
}
