package com.example.myapplication.controller.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.repository.QuestionRepository;
import com.example.myapplication.repository.RepositoryInterface;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_IS_CHEAT = "extraBundleIsCheated";
    public static final String EXTRA_BUNDLE_CURRENT_QUESTION_INDEX = "extraBundleCurrentQuestionIndex";
    private TextView mTextViewAnswer;
    private Button mButtonCheat;
    private Button mButtonBack;
    private RepositoryInterface mRepository;
    private int mCurrentIndex;

    public static Intent newIntent(Context context, int currentQuestionIndex) {
        Intent intent = new Intent(context, CheatActivity.class);
        intent.putExtra(EXTRA_BUNDLE_CURRENT_QUESTION_INDEX, currentQuestionIndex);
        return intent;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        Intent intent = getIntent();
        mCurrentIndex = intent.getIntExtra(EXTRA_BUNDLE_CURRENT_QUESTION_INDEX, 1);
        mRepository = QuestionRepository.getInstance();

        findAllViews();
        setClickListeners();
    }

    private void findAllViews() {
        mTextViewAnswer = findViewById(R.id.text_view_show_answer);
        mButtonCheat = findViewById(R.id.button_show_cheat);
        mButtonBack = findViewById(R.id.button_back);
    }

    private void setClickListeners() {
        mButtonCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRepository.getQuestions().get(mCurrentIndex).isAnswerTrue()) {
                    mTextViewAnswer.setText(R.string.button_true);
                } else {
                    mTextViewAnswer.setText(R.string.button_false);
                }

                mRepository.getQuestions().get(mCurrentIndex).setIsAnswered(true);
            }
        });

        mButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void saveResult(boolean isCheated) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_IS_CHEAT, isCheated);

        //3. save result in activity
        setResult(RESULT_OK, intent);

        //4. os will automatically send result back to parent.
    }
}