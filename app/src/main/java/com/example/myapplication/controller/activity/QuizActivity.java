package com.example.myapplication.controller.activity;

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
import com.example.myapplication.repository.QuestionRepository;
import com.example.myapplication.repository.RepositoryInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    public String intentQuestions;
    private RepositoryInterface mRepository;
    public static final String BUNDLE_KEY_CURRENT_INDEX = "mCurrentIndex";
    public static final String BUNDLE_KEY_CURRENT_SCORE = "mCurrentScore";
    public static final String BUNDLE_KEY_IS_ANSWERED_ARRAY = "mIsAnsweredArray";
    private int mCurrentIndex = 0;
    private int mCurrentScore = 0;
    private List<Question> mQuestionBank;


    //*************************           On Create         **************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        intentQuestions = intent.getStringExtra(QuizBuilderActivity.EXTRA_QUESTIONS);
        mRepository = QuestionRepository.getInstance();
        mQuestionBank = mRepository.getQuestions();

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(BUNDLE_KEY_CURRENT_INDEX);
            mCurrentScore = savedInstanceState.getInt(BUNDLE_KEY_CURRENT_SCORE);

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

        if (mQuestionBank.get(mCurrentIndex).getIsAnswered()) {
            mButtonTrue.setEnabled(false);
            mButtonFalse.setEnabled(false);
        } else {
            mButtonTrue.setEnabled(true);
            mButtonFalse.setEnabled(true);
        }
        Question currentQuestion = mQuestionBank.get(mCurrentIndex);
        mTextViewQuestion.setText(currentQuestion.getTextQuestion());

    }

    //*************************           resetQuestions         **************************
    public void resetQuestions() {
//        for (Question question : mQuestionBank) question.mIsAnswered = false;
        for (int i = 0; i < mQuestionBank.size(); i++)
            mQuestionBank.get(i).setIsAnswered(false);
//        isAnsweredArray.set(i, false);

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
                mQuestionBank.get(mCurrentIndex).setIsAnswered(true);
                mQuestionBank.get(mCurrentIndex).setIsAnswered(true);
            }
        });

        mButtonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                mQuestionBank.get(mCurrentIndex).setIsAnswered(true);
                mQuestionBank.get(mCurrentIndex).setIsAnswered(true);
            }
        });

        mImageButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (++mCurrentIndex) % mQuestionBank.size();
                updateQuestion();
            }
        });

        mImageButtonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (--mCurrentIndex + mQuestionBank.size()) % mQuestionBank.size();
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
                mCurrentIndex = mQuestionBank.size() - 1;
                updateQuestion();
            }
        });
        mTextViewQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (++mCurrentIndex) % mQuestionBank.size();
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
        boolean isAnswerTrue = mQuestionBank.get(mCurrentIndex).isAnswerTrue();
//        boolean isAnswerTrue = isAnsweredArray[mCurrentIndex];
        if (userPressed == isAnswerTrue) {
            Toast.makeText(this, R.string.toast_correct, Toast.LENGTH_LONG).show();
            mCurrentScore++;
            updateScore();

        } else {
            Toast.makeText(this, R.string.toast_incorrect, Toast.LENGTH_LONG).show();

        }
        mQuestionBank.get(mCurrentIndex).setIsAnswered(true);

        mButtonTrue.setEnabled(false);
        mButtonFalse.setEnabled(false);
    }

    private void show() {
        Toast.makeText(this, intentQuestions, Toast.LENGTH_LONG).show();
    }


}
