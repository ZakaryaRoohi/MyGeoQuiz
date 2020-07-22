package com.example.myapplication.repository;

import com.example.myapplication.enums.QuestionTextColor;
import com.example.myapplication.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuestionRepository implements RepositoryInterface {
    private static QuestionRepository sQuestionRepository;
    private static final int NUMBER_OF_QUESTIONS = 20;

    public static QuestionRepository getInstance() {
        if (sQuestionRepository == null) {
            sQuestionRepository = new QuestionRepository();
        }
        return sQuestionRepository;
    }

    private List<Question> mQuestions;

    //Read All

    public List<Question> getQuestions() {
        return mQuestions;
    }
    public void setQuestions(List<Question> questions){
        mQuestions=questions;
    }
    private QuestionRepository(){
        mQuestions= new ArrayList<>();
        for (int i = 0; i <NUMBER_OF_QUESTIONS ; i++) {
            Question question = new Question("tehran is capital of iran",true,
                    false,true,"black");
            mQuestions.add(question);
        }
    }

    public Question getQuestion(UUID uuid){
        for(Question question:mQuestions){
            if(question.getId().equals(uuid))
                return question;
        }
        return null;
    }

    @Override
    public void updateQuestion(Question question) {

    }

    @Override
    public void deleteQuestion(Question question) {

    }

}
