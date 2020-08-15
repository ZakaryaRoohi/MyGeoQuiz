package com.example.myapplication.model;

import android.widget.Switch;

import java.io.Serializable;

public class Setting implements Serializable {

    private int mTextSize;
    private int mColorBackground;
    private int mStateTrueButton;
    private int mStateFalseButton;
    private int mStateNextButton;
    private int mStatePreviousButton;
    private int mStateFirstButton;
    private int mStateLastButton;
    private int mStateCheatButton;
    private int mGameTime;



    public Setting(int textSize, int backgroundColor, int trueButtonState, int falseButtonState,
                   int nextButtonState, int previousButtonState, int firstButtonState,
                   int lastButtonState, int cheatButtonState , int gameTime) {
        this.mTextSize = textSize;
        this.mColorBackground = backgroundColor;
        this.mStateTrueButton = trueButtonState;
        this.mStateFalseButton = falseButtonState;
        this.mStateNextButton = nextButtonState;
        this.mStatePreviousButton = previousButtonState;
        this.mStateFirstButton = firstButtonState;
        this.mStateLastButton = lastButtonState;
        this.mStateCheatButton = cheatButtonState;
        this.mGameTime= gameTime;
    }
    public int getGameTime() {
        return mGameTime;
    }

    public void setGameTime(int gameTime) {
        mGameTime = gameTime;
    }

    public int getTextSize() {
        return mTextSize;
    }

    public void setTextSize(int mTextSize) {
        this.mTextSize = mTextSize;
    }

    public int getColorBackground() {
        return mColorBackground;
    }

    public void setColorBackground(int mColorBackground) {
        this.mColorBackground = mColorBackground;
    }

    public int getStateTrueButton() {
        return mStateTrueButton;
    }

    public void setStateTrueButton(int mStateTrueButton) {
        this.mStateTrueButton = mStateTrueButton;
    }

    public int getStateFalseButton() {
        return mStateFalseButton;
    }

    public void setStateFalseButton(int mStateFalseButton) {
        this.mStateFalseButton = mStateFalseButton;
    }

    public int getStateNextButton() {
        return mStateNextButton;
    }

    public void setStateNextButton(int mStateNextButton) {
        this.mStateNextButton = mStateNextButton;
    }

    public int getStatePreviousButton() {
        return mStatePreviousButton;
    }

    public void setStatePreviousButton(int mStatePreviousButton) {
        this.mStatePreviousButton = mStatePreviousButton;
    }

    public int getStateFirstButton() {
        return mStateFirstButton;
    }

    public void setStateFirstButton(int mStateFirstButton) {
        this.mStateFirstButton = mStateFirstButton;
    }

    public int getStateLastButton() {
        return mStateLastButton;
    }

    public void setStateLastButton(int mStateLastButton) {
        this.mStateLastButton = mStateLastButton;
    }
    public int getStateCheatButton() {
        return mStateCheatButton;
    }

    public void setStateCheatButton(int mStateCheatButton) {
        this.mStateCheatButton = mStateCheatButton;
    }

    @Override
    public String toString() {
        return "Setting{" +
                "mTextSize=" + mTextSize +
                ", mColorBackground=" + mColorBackground +
                ", mStateTrueButton=" + mStateTrueButton +
                ", mStateFalseButton=" + mStateFalseButton +
                ", mStateNextButton=" + mStateNextButton +
                ", mStatePreviousButton=" + mStatePreviousButton +
                ", mStateFirstButton=" + mStateFirstButton +
                ", mStateLastButton=" + mStateLastButton +
                ", mStateCheatButton=" + mStateCheatButton +
                '}';
    }
}