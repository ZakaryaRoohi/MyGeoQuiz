package com.example.myapplication.repository;

import com.example.myapplication.model.Question;

import java.util.List;
import java.util.UUID;

public interface RepositoryInterface {
    List<Question> getQuestions();

    Question getList(UUID uuid);

    void updateQuestion(Question question);
    void deleteQuestion(Question question);
}