package com.github.worms308.surveyapp.service;

import com.github.worms308.surveyapp.model.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerService {
    Optional<Answer> findById(long id);
    List<Answer> findAll();
    Answer add(Answer answer);
}
