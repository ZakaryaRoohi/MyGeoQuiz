package com.example.myapplication.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.model.Setting;
import com.example.myapplication.repository.UserRepository;


public class SettingActivity extends AppCompatActivity {
    public static final String EXTRA_CAL_BACK_SETTING = "ExtraCalBackSetting";
    public static final String SETTING_BUNDLE = "SettingBundle";
    public static final String EXTRA_SAVED_TIME = "ExtraSavedTime";

    private RadioButton mSmallSize;
    private RadioButton mMediumSize;
    private RadioButton mLargeSize;
    private RadioButton mLightBlue;
    private RadioButton mLightRed;
    private RadioButton mLightGreen;
    private RadioButton mWight;
    private Switch mTrueButton;
    private Switch mFalseButton;
    private Switch mNextButton;
    private Switch mPreviousButton;
    private Switch mFirstButton;
    private Switch mLastButton;
    private Switch mCheatButton;
    private Button mButtonSave;
    private Button mDiscard;
    private int mTime;
    private UserRepository mUserRepository;

    private Boolean mIsSaved = false;
    private Setting mSettingNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        findViews();
        Intent intent = getIntent();
//        mSettingNew = (Setting) intent.getSerializableExtra(QuizActivity.EXTRA_SETTING_STATES);
        mUserRepository = UserRepository.getInstance();
        mSettingNew = mUserRepository.getSetting();

        mTime = intent.getIntExtra(QuizActivity.BUNDLE_KEY_CURRENT_TIME,15);
        setCurrentStateQuizActivity();
        if (savedInstanceState != null) {
            mSettingNew = (Setting) savedInstanceState.get(SETTING_BUNDLE);

        }

        setListeners();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SETTING_BUNDLE, mSettingNew);


    }



    private void findViews() {
        mSmallSize = findViewById(R.id.radio_botton_small);
        mMediumSize = findViewById(R.id.radio_botton_medium);
        mLargeSize = findViewById(R.id.radio_botton_large);
        mLightBlue = findViewById(R.id.radio_botton_light_blue);
        mLightRed = findViewById(R.id.radio_botton_light_red);
        mLightGreen = findViewById(R.id.radio_botton_light_green);
        mWight = findViewById(R.id.radio_botton_wight);
        mTrueButton = findViewById(R.id.change_state_true_button);
        mFalseButton = findViewById(R.id.change_state_false_button);
        mNextButton = findViewById(R.id.change_state_next_button);
        mPreviousButton = findViewById(R.id.change_state_previous_button);
        mFirstButton = findViewById(R.id.change_state_first_button);
        mLastButton = findViewById(R.id.change_state_last_button);
        mCheatButton = findViewById(R.id.change_state_cheat_button);
        mButtonSave = findViewById(R.id.botton_save);
        mDiscard = findViewById(R.id.botton_discard);
    }

    private void setListeners() {
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIsSaved = true;
                setChange();
                SettingActivity.this.finish();

            }
        });
        mDiscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingActivity.this.finish();

            }
        });

        mSmallSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((RadioButton) view).isChecked();
                // Check which radiobutton was pressed
                if (checked) {
                    mSettingNew.setTextSize(16);
                }

            }
        });
        mMediumSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((RadioButton) view).isChecked();
                // Check which radiobutton was pressed
                if (checked) {
                    mSettingNew.setTextSize(18);
                }

            }
        });
        mLargeSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((RadioButton) view).isChecked();
                // Check which radiobutton was pressed
                if (checked) {
                    mSettingNew.setTextSize(24);

                }

            }
        });
        mLightBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mColorBackground=R.color.colorLightBlue;
                mSettingNew.setColorBackground(R.color.colorLightBlue);
            }
        });
        mLightRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mColorBackground=R.color.colorLightRed;
                mSettingNew.setColorBackground(R.color.colorLightRed);
            }
        });
        mLightGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mColorBackground=R.color.colorLightGreen;
                mSettingNew.setColorBackground(R.color.colorLightGreen);
            }
        });
        mWight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mColorBackground=R.color.colorWight;
                mSettingNew.setColorBackground(R.color.colorWight);
            }
        });
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTrueButton.isChecked())
                    mSettingNew.setStateTrueButton(0);
                else
                    mSettingNew.setStateTrueButton(4);
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFalseButton.isChecked())
                    mSettingNew.setStateFalseButton(0);
                else
                    mSettingNew.setStateFalseButton(4);
            }
        });
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mNextButton.isChecked())
                    mSettingNew.setStateNextButton(0);
                else
                    mSettingNew.setStateNextButton(4);
            }
        });
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPreviousButton.isChecked())
                    mSettingNew.setStatePreviousButton(0);
                else
                    mSettingNew.setStatePreviousButton(4);
            }
        });
        mFirstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFirstButton.isChecked())
                    mSettingNew.setStateFirstButton(0);
                else
                    mSettingNew.setStateFirstButton(4);
            }
        });
        mLastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mLastButton.isChecked())
                    mSettingNew.setStateLastButton(0);
                else
                    mSettingNew.setStateLastButton(4);
            }
        });
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCheatButton.isChecked())
                    mSettingNew.setStateCheatButton(0);
                else
                    mSettingNew.setStateCheatButton(4);
            }
        });

    }

    private void setChange() {
        if (mIsSaved) {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_CAL_BACK_SETTING, mSettingNew);
            intent.putExtra(EXTRA_SAVED_TIME,mTime);
            mUserRepository.SetSetting(mSettingNew);
            setResult(RESULT_OK, intent);

        }
    }
    private void setCurrentStateQuizActivity(){
        setRadioButtonStateTextSize(mSettingNew.getTextSize());
        setRadioButtonStateColorBackground(mSettingNew.getColorBackground());
        setSwitchButtonStateButtons(mTrueButton,mSettingNew.getStateTrueButton());
        setSwitchButtonStateButtons(mFalseButton,mSettingNew.getStateFalseButton());
        setSwitchButtonStateButtons(mNextButton,mSettingNew.getStateNextButton());
        setSwitchButtonStateButtons(mPreviousButton,mSettingNew.getStatePreviousButton());
        setSwitchButtonStateButtons(mFirstButton,mSettingNew.getStateFirstButton());
        setSwitchButtonStateButtons(mLastButton,mSettingNew.getStateLastButton());
        setSwitchButtonStateButtons(mCheatButton,mSettingNew.getStateCheatButton());
    }

    private void setRadioButtonStateTextSize( int size) {
        if (size==16)
            mSmallSize.setChecked(true);
        if (size==18)
            mMediumSize.setChecked(true);
        if (size==24)
            mLargeSize.setChecked(true);
    }
    private void setRadioButtonStateColorBackground(int colorBackgroundNumber){
        if (colorBackgroundNumber==R.color.colorLightBlue)
            mLightBlue.setChecked(true);
        if (colorBackgroundNumber==R.color.colorLightRed)
            mLightRed.setChecked(true);
        if (colorBackgroundNumber==R.color.colorLightGreen)
            mLightGreen.setChecked(true);
        if (colorBackgroundNumber==R.color.colorWight)
            mWight.setChecked(true);
    }
    //private void setRadioButton(Rad
    // ioButton radioButton,int )

    private void setSwitchButtonStateButtons(Switch switchButton,int StateButton) {
        if ( StateButton==0)
            switchButton.setChecked(true);
        else
            switchButton.setChecked(false);
    }

}