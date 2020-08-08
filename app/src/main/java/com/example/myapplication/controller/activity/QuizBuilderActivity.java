package com.example.myapplication.controller.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.model.Question;
import com.example.myapplication.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.List;

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
                if (mEditText.getText().toString() == null || mEditText.getText().toString().length() == 0) {
                    Toast.makeText(QuizBuilderActivity.this, "Invalid Input", Toast.LENGTH_SHORT).show();
                } else {
                    String stringQuestions = mEditText.getText().toString();
//                Intent intent = new Intent(QuizBuilderActivity.this,QuizActivity.class);
//                intent.putExtra(EXTRA_QUESTIONS,questions);
//                startActivity(intent);
                    List<Question> questionList = parseQuestions(stringQuestions);
                    QuestionRepository.getInstance().setQuestions(questionList);
                    Intent intent = new Intent(QuizBuilderActivity.this, QuestionListActivity.class);
                    startActivity(intent);
                }


            }
        });
    }

    private static ArrayList<Question> parseQuestions(String s) {
        ArrayList<Question> mQuestionsBank = new ArrayList<>();
        int firstBracket;
        int secondBracket;
        while (s.contains("[")) {
            firstBracket = s.indexOf("[");
            secondBracket = s.indexOf("]");
            String question = s.substring(firstBracket + 1, secondBracket);
            s = s.substring(secondBracket + 1);
            String[] strings = question.split(",");
            Question question1 = new Question();
            question1.setTextQuestion(strings[0].substring(strings[0].indexOf("{") + 2, strings[0].indexOf("}") - 1));
            question1.setAnswerTrue(Boolean.parseBoolean(strings[1].substring(strings[1].indexOf("{") + 1, strings[1].indexOf("}"))));
            question1.setCanCheat(Boolean.parseBoolean(strings[2].substring(strings[2].indexOf("{") + 1, strings[2].indexOf("}"))));
            question1.setQuestionTextColor(strings[3].substring(strings[3].indexOf("{") + 1, strings[3].indexOf("}")));
            mQuestionsBank.add(question1);

        }
        return mQuestionsBank;
    }

}