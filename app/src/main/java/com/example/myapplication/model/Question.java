package com.example.myapplication.model;

import com.example.myapplication.enums.QuestionTextColor;

import java.util.UUID;

public class Question {
    private String mTextQuestion;
    private boolean mAnswerTrue;
    private boolean mIsAnswered;
    private boolean mCanCheat;
    private UUID mId;
    private String mQuestionTextColor;

    public Question() {

    }

    public void setIsAnswered(boolean answered) {
        mIsAnswered = answered;
    }

    public void setCanCheat(boolean canCheat) {
        mCanCheat = canCheat;
    }

    public void setQuestionTextColor(String questionTextColor) {
        mQuestionTextColor = questionTextColor;
    }

    public String getQuestionTextColor() {
        return mQuestionTextColor;
    }

    public boolean getCanCheat() {
        return mCanCheat;
    }

    public boolean getIsAnswered() {
        return mIsAnswered;
    }

    public String getTextQuestion() {
        return mTextQuestion;
    }

    public void setTextQuestion(String mTextQuestion) {
        this.mTextQuestion = mTextQuestion;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public boolean getAnswerTrue(){return mAnswerTrue;}
    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;

    }

    public UUID getId() {
        return mId;
    }

    public Question(String textQuestion, boolean answerTrue, boolean isAnswered, boolean canCheat, String questionTextColor) {
        mId=UUID.randomUUID();
        mTextQuestion = textQuestion;
        mAnswerTrue = answerTrue;
        mIsAnswered = isAnswered;
        mCanCheat = canCheat;
        mQuestionTextColor = questionTextColor;
    }
}
