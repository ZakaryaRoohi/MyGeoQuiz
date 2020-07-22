package com.example.myapplication.model;

public class Question {
    public String mTextQuestion;
    public boolean mAnswerTrue;
    public boolean mIsAnswered;
    public boolean mCanCheat;
    public String mColor;


    public String getTextQuestion() {
        return mTextQuestion;
    }

    public void setTextQuestion(String mTextQuestion) {
        this.mTextQuestion = mTextQuestion;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;

    }

    public Question(String textQuestion, boolean answerTrue, boolean isAnswered, boolean canCheat, String color) {
        mTextQuestion = textQuestion;
        mAnswerTrue = answerTrue;
        mIsAnswered = isAnswered;
        mCanCheat = canCheat;
        mColor = color;
    }
}
