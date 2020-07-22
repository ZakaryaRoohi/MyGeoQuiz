package com.example.myapplication.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.controller.fragment.QuestionListFragment;

public class QuestionListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Question List");
        setContentView(R.layout.activity_question_list);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if(fragment==null){
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container,new QuestionListFragment())
                    .commit();
        }
    }
}