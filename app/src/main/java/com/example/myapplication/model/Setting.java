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

    public Setting(int mTextSize, int mColorBackground, int mStateTrueButton, int mStateFalseButton,
                   int mStateNextButton, int mStatePreviousButton, int mStateFirstButton,
                   int mStateLastButton, int mStateCheatButton) {
        this.mTextSize = mTextSize;
        this.mColorBackground = mColorBackground;
        this.mStateTrueButton = mStateTrueButton;
        this.mStateFalseButton = mStateFalseButton;
        this.mStateNextButton = mStateNextButton;
        this.mStatePreviousButton = mStatePreviousButton;
        this.mStateFirstButton = mStateFirstButton;
        this.mStateLastButton = mStateLastButton;
        this.mStateCheatButton = mStateCheatButton;
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
}