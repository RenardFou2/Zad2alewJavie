package com.example.zad1alewjavie;

public class Question {
    private int questionId;
    private boolean trueAnswer;

    public Question(int qID, boolean ta){
        questionId=qID;
        trueAnswer=ta;
    }
    public boolean getCorrectAnswer(){
        return trueAnswer;
    }
    public int getQuestionId(){
        return questionId;
    }
}
