package com.example.myapplication.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

public class QuizBuilderActivity extends AppCompatActivity {
    public static final String EXTRA_QUESTIONS = "questions";
    private Button mButtonStart;
    private EditText mEditText;
//    private String questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_builder);

        FindAllView();
        setOnClickListener();

    }

    private void FindAllView() {
        mButtonStart = findViewById(R.id.button_start);
        mEditText = findViewById(R.id.edit_text_questions);
    }
//"{[{“Tehran in iran”}, {true}, {false}, {green}],[{“iran language is english”}, {false} {true}, {red}], [{“England is in usa”}, {false}, {false}, {black}] , {30}";
    private void setOnClickListener() {
        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String questions = mEditText.getText().toString();
                Intent intent = new Intent(QuizBuilderActivity.this,QuizActivity.class);
                intent.putExtra(EXTRA_QUESTIONS,questions);
                startActivity(intent);

            }
        });
    }


}