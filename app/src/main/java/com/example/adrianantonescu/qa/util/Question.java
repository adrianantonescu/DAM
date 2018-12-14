package com.example.adrianantonescu.qa.util;

public abstract class Question {

    protected int id;
    protected String subject;
    protected String questionText;

    public Question(int id, String subject, String questionText) {
        this.id = id;
        this.subject = subject;
        this.questionText = questionText;
    }

    public String getQuestionText() {
        return questionText;
    }

    public int getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }
}
