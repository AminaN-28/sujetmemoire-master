package com.example.myquestionapp;

public class QuestionTab {
    private boolean answer;
    private int question;

    public QuestionTab(int Question, boolean Answer){
        this.answer=answer;
        this.question=question;
    }
    public int getQuestion() {
        return question;
    }
    public boolean isAnswer() {
        return answer;
    }
}
