package com.example.evan.caklontonggg;

/**
 * Created by evan on 3/25/17.
 */

public class Question {
    private String mCorrectAnswer;
    private int mQuestions;


    public Question(int question, String correctAnswer){
        mQuestions = question;
        mCorrectAnswer = correctAnswer;
    }

    public int getQuestion(){
        return mQuestions;
    }

    public void setQuestion(int question){
        mQuestions = question;
    }

    public String isCorrectAnswer(){
        return mCorrectAnswer;
    }

    public void setCorrectAnswer (String correctAnswer) {
        mCorrectAnswer = correctAnswer;
    }

}