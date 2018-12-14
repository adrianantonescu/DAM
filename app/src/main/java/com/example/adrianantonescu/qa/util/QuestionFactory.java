package com.example.adrianantonescu.qa.util;

import java.util.ArrayList;

public class QuestionFactory {
    private static QuestionFactory factory = new QuestionFactory();
    private static int lastId;
    Question question;

    public static QuestionFactory getInstance() {

        if(factory == null) {
            synchronized (QuestionFactory.class) {
                if(factory == null) {
                    factory = new QuestionFactory();
                }
            }
        }
        return factory;
    }


    private QuestionFactory() {
        lastId = 0;
    }

    public Question createQuestion(String type, String ...args) {
        try {
            if (type == "simple") {
                int id = lastId;
                String subject = args[0];
                String questionText = args[1];
                ArrayList<String> var = new ArrayList<>();
                var.add(args[2]);
                var.add(args[3]);
                var.add(args[4]);
                var.add(args[5]);
                String varCorecta = args[6];
                question = new SimpleQuestion(id, subject, questionText, var, varCorecta);
            } else if (type == "multiple") {
                int id = lastId;
                String subject = args[0];
                String questionText = args[1];
                ArrayList<String> var = new ArrayList<>();
                var.add(args[2]);
                var.add(args[3]);
                var.add(args[4]);
                var.add(args[5]);
                ArrayList<String> varC = new ArrayList<>();
                for (int i = 6; i < args.length; i++) {
                    varC.add(args[i]);
                }
                question = new MultipleQuestion(id, subject, questionText, var, varC);
            } else {
                int id = lastId;
                String subject = args[0];
                String questionText = args[1];
                String questionAnswer = args[2];
                question = new OpenQuestion(id, subject, questionText, questionAnswer);
            }
            lastId++;
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return question;
    }
}
