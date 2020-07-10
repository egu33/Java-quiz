package com.example.quizapp;

//object
public class Quiz {
    private String question;
    private Boolean answer;

    public Quiz(String question, boolean answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public Boolean isAnswer() {
        return answer;
    }

    //Junit
    @Override
    public String toString() {
        String marubatsu = answer ? "○" : "×";
//        String marubatsu;
//        if(answer){
//            marubatsu = "○";
//        }
//        else{
//            marubatsu = "×";
//        }
        return question + " " + marubatsu;
    }

    public static Quiz fromString(String line) {
        String question = line.substring(0, line.length() - 2);
        boolean answer = line.endsWith("○");
        return new Quiz(question, answer);
    }
}
