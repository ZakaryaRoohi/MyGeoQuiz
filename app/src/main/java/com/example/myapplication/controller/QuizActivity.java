package com.example.myapplication.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.model.Question;

import java.util.ArrayList;
import java.util.Arrays;

public class QuizActivity extends AppCompatActivity {

    private Button mButtonTrue;
    private Button mButtonFalse;
    private TextView mTextViewQuestion;
    private Button mButtonLast;
    private Button mButtonFirst;
    private ImageButton mImageButtonNext;
    private ImageButton mImageButtonPrevious;
    private TextView mTextViewScore;
    private Button mButtonReset;
//        public String intentQuestions = "{[{“Tehran in iran”}, {true}, {false}, {green}],[{“iran language is english”}, {false} {true}, {red}], [{“England is in usa”}, {false}, {false}, {black}]} , {30}";
    public String intentQuestions;

    public static final String BUNDLE_KEY_CURRENT_INDEX = "mCurrentIndex";
    public static final String BUNDLE_KEY_CURRENT_SCORE = "mCurrentScore";
    public static final String BUNDLE_KEY_IS_ANSWERED_ARRAY = "mIsAnsweredArray";
    private int mCurrentIndex = 0;
    private int mCurrentScore = 0;
    boolean[] isAnsweredArray = {false, false, false};
    private Question[] mQuestionBank;

    //*************************           On Create         **************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        intentQuestions = intent.getStringExtra(QuizBuilderActivity.EXTRA_QUESTIONS);
        mQuestionBank = parseStringToQuestionArray(intentQuestions);
//        intentQuestions = "{[{“Tehran in iran”}, {true}, {false}, {green}],[{“iran language is english”}, {false} {true}, {red}], [{“England is in usa”}, {false}, {false}, {black}] , {30}";
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(BUNDLE_KEY_CURRENT_INDEX);
            mCurrentScore = savedInstanceState.getInt(BUNDLE_KEY_CURRENT_SCORE);
            isAnsweredArray = savedInstanceState.getBooleanArray(BUNDLE_KEY_IS_ANSWERED_ARRAY);
            for (int i = 0; i < isAnsweredArray.length; i++) {
                mQuestionBank[i].mIsAnswered = isAnsweredArray[i];
            }
        }

//        show();

        setContentView(R.layout.activity_quiz);
        //it must be the first task we do after inflate
        findAllViews();
        setClickListeners();

        updateQuestion();
        updateScore();
    }

    //*************************           onSaveInstanceState         **************************
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BUNDLE_KEY_CURRENT_INDEX, mCurrentIndex);
        outState.putInt(BUNDLE_KEY_CURRENT_SCORE, mCurrentScore);
        outState.putBooleanArray(BUNDLE_KEY_IS_ANSWERED_ARRAY, isAnsweredArray);
    }

    //*************************           findAllViews         **************************
    private void findAllViews() {
        mButtonTrue = findViewById(R.id.button_true);
        mButtonFalse = findViewById(R.id.button_false);
        mTextViewQuestion = findViewById(R.id.text_view_question);
        mButtonLast = findViewById(R.id.button_last);
        mButtonFirst = findViewById(R.id.button_first);
        mImageButtonNext = findViewById(R.id.imageButton_next);
        mImageButtonPrevious = findViewById(R.id.imageButton_previous);
        mTextViewScore = findViewById(R.id.textView_score);
        mButtonReset = findViewById(R.id.button_reset);

    }

    //*************************           updateQuestion         **************************
    private void updateQuestion() {

        if (isAnsweredArray[mCurrentIndex]) {
            mButtonTrue.setEnabled(false);
            mButtonFalse.setEnabled(false);
        } else {
            mButtonTrue.setEnabled(true);
            mButtonFalse.setEnabled(true);
        }
        Question currentQuestion = mQuestionBank[mCurrentIndex];
        mTextViewQuestion.setText(currentQuestion.getTextQuestion());

    }

    //*************************           resetQuestions         **************************
    public void resetQuestions() {
//        for (Question question : mQuestionBank) question.mIsAnswered = false;
        for (int i = 0; i < isAnsweredArray.length; i++)
            isAnsweredArray[i] = false;
    }

    //*************************           updateScore         **************************
    private void updateScore() {
        String string = "امتياز شما: " + mCurrentScore + "";
        mTextViewScore.setText(string);

    }

    //*************************           setClickListeners         **************************
    private void setClickListeners() {
        mButtonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                isAnsweredArray[mCurrentIndex] = true;
                mQuestionBank[mCurrentIndex].mIsAnswered = true;
            }
        });

        mButtonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                isAnsweredArray[mCurrentIndex] = true;
                mQuestionBank[mCurrentIndex].mIsAnswered = true;
            }
        });

        mImageButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (++mCurrentIndex) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mImageButtonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (--mCurrentIndex + mQuestionBank.length) % mQuestionBank.length;
                updateQuestion();
            }
        });
        mButtonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = 0;
                updateQuestion();
            }
        });
        mButtonLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = mQuestionBank.length - 1;
                updateQuestion();
            }
        });
        mTextViewQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (++mCurrentIndex) % mQuestionBank.length;
                updateQuestion();
            }
        });
        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String string = "امتياز شما: " + mCurrentScore + "";
                mTextViewScore.setText(string);
                mCurrentScore = 0;
                updateScore();
                resetQuestions();
                mCurrentIndex = 0;
                updateQuestion();

            }
        });
    }

    //*************************           checkAnswer         **************************
    private void checkAnswer(boolean userPressed) {
        boolean isAnswerTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
//        boolean isAnswerTrue = isAnsweredArray[mCurrentIndex];
        if (userPressed == isAnswerTrue) {
            Toast.makeText(this, R.string.toast_correct, Toast.LENGTH_LONG).show();
            mCurrentScore++;
            updateScore();

        } else {
            Toast.makeText(this, R.string.toast_incorrect, Toast.LENGTH_LONG).show();

        }
//        mQuestionBank[mCurrentIndex].mIsAnswered=true;
        isAnsweredArray[mCurrentIndex] = true;
        mButtonTrue.setEnabled(false);
        mButtonFalse.setEnabled(false);
    }

    private void show() {
        Toast.makeText(this, intentQuestions, Toast.LENGTH_LONG).show();
    }

    private  Question[] parseStringToQuestionArray(String inputQuestion) {
        String question, color;
        boolean trueAnswer, canCheat;
        int numberOfQuestion = 0;
        for (int i = 0; i < inputQuestion.length() - 1; i++) {
            if (inputQuestion.charAt(i) == ']')
                numberOfQuestion++;
        }
        Question[] questions = new Question[numberOfQuestion];
//                  "['{'-\\[-',']"
        inputQuestion = inputQuestion.replaceAll("['{'-'\\['-,']", "");
        String[] arrayOfString = inputQuestion.split("]");
        for (int i = 0; i < numberOfQuestion; i++) {
//            System.out.println(arrayOfString[i]);
            String[] insideArray = arrayOfString[i].split("}");
//            for (int j = 0; j < insideArray.length; j++) {
//
//                System.out.println(insideArray[j]);
//
//            }
            try {
                question = insideArray[0];
                trueAnswer = stringToBoolean(insideArray[1]);
                canCheat = stringToBoolean(insideArray[2]);
                color = insideArray[3];

                questions[i] = new Question(question, trueAnswer, false, canCheat, color);
            } catch (IndexOutOfBoundsException exc) {

            }
//            System.out.println("*************************");

        }
        return questions;
    }

    private   boolean stringToBoolean(String str) {
        boolean result;
        if (str.contains("true"))
            result = true;
        else
            result = false;
        return result;
    }

//    public static void main(String[] args) {
//        String questions = "{[{\"Tehran in iran\"}, {true}, {false}, {green}],[{\"iran language is english\"}, {false} {true}, {red}], [{\"England is in usa\"}, {false}, {false}, {black}] , {30}";
//        Question[] q = parseStringToQuestionArray(questions);
//        for (int i = 0; i < q.length; i++) {
//            System.out.println(q[i].getTextQuestion() + "    " + q[i].mAnswerTrue
//                    + "  " + q[i].mIsAnswered + "     " + q[i].mCanCheat + "      " + q[i].mColor);
//        }
//        System.out.println(Arrays.toString(q));
//    }
//    {[{“Tehran in iran”}, {true}, {false}, {green}],[{“iran language is english”}, {false} {true}, {red}], [{“England is in usa”}, {false}, {false}, {black}]} , {30}

}
