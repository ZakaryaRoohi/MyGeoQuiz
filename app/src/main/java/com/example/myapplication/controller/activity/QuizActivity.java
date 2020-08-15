package com.example.myapplication.controller.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.model.Question;
import com.example.myapplication.model.Setting;
import com.example.myapplication.repository.QuestionRepository;
import com.example.myapplication.repository.RepositoryInterface;

public class QuizActivity extends AppCompatActivity {

    public static final String BUNDLE_KEY_CURRENT_TIME = "bundleKeyCurrentTime";
    private static final String NUMBER_OF_ANSWERED = "numberOfAnswered";
    private static final int REQUEST_CODE_SETTING =1 ;
    public static final String EXTRA_SETTING_STATES = "com.example.MyGeoQuiz2.settingStates";
    public static final String EXTRA_CURRENT_TIME = "ExtraCurrentTime";
    private Button mButtonTrue;
    private Button mButtonFalse;
    private TextView mTextViewQuestion;
    private Button mButtonLast;
    private Button mButtonFirst;
    private ImageButton mImageButtonNext;
    private ImageButton mImageButtonPrevious;
    private TextView mTextViewScore;
    private Button mButtonReset;
    private Button mButtonCheat;
    private TextView mTextViewTimer;
    private TextView mTextViewSetting;
    private Button mButtonRestartHide;
    private RepositoryInterface mRepository;
    public static final String BUNDLE_KEY_CURRENT_INDEX = "mCurrentIndex";
    public static final String BUNDLE_KEY_CURRENT_SCORE = "mCurrentScore";
    public static final String EXTRA_BUNDLE_QUESTIONS = "extraBundleQuestions";

    private LinearLayout mLayout1;
    private LinearLayout mLayout2;
    private Button mButtonRestart;
    private int mCurrentIndex = 0;
    private int mCurrentScore = 0;
    private CountDownTimer mCountDownTimer;
    private TextView mTextViewScoreHide;
    private int mGameTime;
    private int mCurrentTime;
    private int mNumberOfAnswered = 0;
    private Setting mSetting =new Setting(18,R.color.colorWight,0,
            0,0,0,0,0,0);
    //*************************           On Create         **************************
    public static Intent newIntent(Context context, String stringQuestions) {
        Intent intent = new Intent(context, QuizActivity.class);
        intent.putExtra(EXTRA_BUNDLE_QUESTIONS, stringQuestions);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent = getIntent();
        String stringQuestions = intent.getStringExtra(EXTRA_BUNDLE_QUESTIONS);
        mGameTime = getTime(stringQuestions);
//        List<Question> questionList = parseQuestions(stringQuestions);
//        QuestionRepository.getInstance().setQuestions(questionList);
        mRepository = QuestionRepository.getInstance();
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(BUNDLE_KEY_CURRENT_INDEX);
            mCurrentScore = savedInstanceState.getInt(BUNDLE_KEY_CURRENT_SCORE);
            mGameTime = savedInstanceState.getInt(BUNDLE_KEY_CURRENT_TIME);
            mNumberOfAnswered = savedInstanceState.getInt(NUMBER_OF_ANSWERED);

        }
        setContentView(R.layout.activity_quiz);
        findAllViews();
        setClickListeners();

        updateQuestion();
        updateScore();
        setSetting();


        mCountDownTimer = new CountDownTimer(mGameTime * 1000, 1000) { // adjust the milli seconds here

            @SuppressLint("SetTextI18n")
            public void onTick(long millisUntilFinished) {

                mTextViewTimer.setText("time :" + millisUntilFinished / 1000);
                mCurrentTime = (int) (millisUntilFinished / 1000);
            }

            public void onFinish() {
                timeOver();

            }
        }.start();


    }

    @Override
    protected void onPause() {
        super.onPause();
        mCountDownTimer.cancel();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mCountDownTimer.start();
        updateQuestion();

    }


    private void timeOver() {
        mLayout1.setVisibility(View.INVISIBLE);
        mLayout2.setVisibility(View.VISIBLE);
        String string = "Finish!!! Score :  " + mCurrentScore + "";
        mTextViewScoreHide.setText(string);
    }

    private void checkGameOver() {
        if (mNumberOfAnswered == mRepository.getQuestions().size()) {
            mLayout1.setVisibility(View.INVISIBLE);
            mLayout2.setVisibility(View.VISIBLE);
            String string = "Finish!!! Score :  " + mCurrentScore + "";
            mNumberOfAnswered=0;
            mTextViewScoreHide.setText(string);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK || data == null)
            return;
        if(requestCode==REQUEST_CODE_SETTING){
            mSetting= (Setting) data.getSerializableExtra(SettingActivity.EXTRA_CAL_BACK_SETTING);
            mGameTime= data.getIntExtra(SettingActivity.EXTRA_SAVED_TIME,10);
            setSetting();
            updateQuestion();


        }
    }

    //*************************           onSaveInstanceState         **************************
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BUNDLE_KEY_CURRENT_INDEX, mCurrentIndex);
        outState.putInt(BUNDLE_KEY_CURRENT_SCORE, mCurrentScore);
        outState.putInt(BUNDLE_KEY_CURRENT_TIME, mCurrentTime);
        outState.putInt(NUMBER_OF_ANSWERED, mNumberOfAnswered);

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
        mTextViewTimer = findViewById(R.id.textView_timer);
        mLayout1 = findViewById(R.id.layout1);
        mLayout2 = findViewById(R.id.layout2);
        mButtonRestart = findViewById(R.id.button_restart_hide);
        mTextViewScoreHide = findViewById(R.id.textView_score_hide);
        mButtonCheat = findViewById(R.id.button_cheat);
        mTextViewSetting = findViewById(R.id.textView_setting);


    }
    private void setSetting() {
        mLayout1.setBackgroundColor(getResources().getColor(mSetting.getColorBackground()));
        mButtonTrue.setVisibility(mSetting.getStateTrueButton());
        mButtonFalse.setVisibility(mSetting.getStateFalseButton());
        mImageButtonNext.setVisibility(mSetting.getStateNextButton());
        mImageButtonPrevious.setVisibility(mSetting.getStatePreviousButton());
        mButtonFirst.setVisibility(mSetting.getStateFirstButton());
        mButtonLast.setVisibility(mSetting.getStateLastButton());
        mButtonCheat.setVisibility(mSetting.getStateCheatButton());
    }

    //*************************           updateQuestion         **************************
    private void updateQuestion() {
        Question currentQuestion = mRepository.getQuestions().get(mCurrentIndex);
        mTextViewQuestion.setText(currentQuestion.getTextQuestion());

        if (mRepository.getQuestions().get(mCurrentIndex).getIsAnswered()) {
            mButtonTrue.setEnabled(false);
            mButtonFalse.setEnabled(false);
        } else {
            mButtonTrue.setEnabled(true);
            mButtonFalse.setEnabled(true);
        }
        checkGameOver();

    }

    //*************************           resetQuestions         **************************
    public void resetQuestions() {
        for (int i = 0; i < mRepository.getQuestions().size(); i++)
            mRepository.getQuestions().get(i).setIsAnswered(false);

    }

    //*************************           updateScore         **************************
    private void updateScore() {
        String string = "Score :  " + mCurrentScore + "";
        mTextViewScore.setText(string);

    }

    //*************************           setClickListeners         **************************
    private void setClickListeners() {
        mButtonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                ++mNumberOfAnswered;
                updateQuestion();
            }
        });

        mButtonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                ++mNumberOfAnswered;
                updateQuestion();
            }
        });

        mImageButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (++mCurrentIndex) % mRepository.getQuestions().size();
                updateQuestion();
            }
        });

        mImageButtonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (--mCurrentIndex + mRepository.getQuestions().size()) % mRepository.getQuestions().size();
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
                mCurrentIndex = mRepository.getQuestions().size() - 1;
                updateQuestion();
            }
        });
        mTextViewQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (++mCurrentIndex) % mRepository.getQuestions().size();
                updateQuestion();
            }
        });
        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCurrentScore = 0;
                updateScore();
                resetQuestions();
                mCurrentIndex = 0;
                updateQuestion();


            }
        });
        mButtonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLayout1.setVisibility(View.VISIBLE);
                mLayout2.setVisibility(View.INVISIBLE);
                mCurrentScore = 0;
                updateScore();
                resetQuestions();
                mCurrentIndex = 0;
                updateQuestion();
                mCountDownTimer.start();


            }
        });
        mButtonCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = CheatActivity.newIntent(QuizActivity.this, mCurrentIndex);
                startActivity(intent);
            }
        });
        mTextViewSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity.this, SettingActivity.class);
                intent.putExtra(EXTRA_SETTING_STATES, mSetting);
                intent.putExtra(BUNDLE_KEY_CURRENT_TIME, mCurrentTime);
                startActivityForResult(intent, REQUEST_CODE_SETTING);
            }
        });


    }

    //*************************           checkAnswer         **************************
    private void checkAnswer(boolean userPressed) {
        boolean isAnswerTrue = mRepository.getQuestions().get(mCurrentIndex).isAnswerTrue();
        if (userPressed == isAnswerTrue) {
            Toast.makeText(this, R.string.toast_correct, Toast.LENGTH_LONG).show();
            mCurrentScore++;
            updateScore();

        } else {
            Toast.makeText(this, R.string.toast_incorrect, Toast.LENGTH_LONG).show();

        }
        mRepository.getQuestions().get(mCurrentIndex).setIsAnswered(true);


    }


    private static int getTime(String string) {
        int lastOpenBrace = string.lastIndexOf('{');
        int lastCloseBrace = string.lastIndexOf('}');
        return Integer.parseInt(string.substring(lastOpenBrace + 1, lastCloseBrace));
    }


}
