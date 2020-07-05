package com.github.worms308.surveyapp.service;

import com.github.worms308.surveyapp.model.ChosenAnswer;

import java.util.Optional;
import java.util.Set;

public interface ChosenAnswerService extends Service<ChosenAnswer> {
    Optional<Set<ChosenAnswer>> findByUserSurveyId(long id);
}
