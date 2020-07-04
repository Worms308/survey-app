package com.github.worms308.surveyapp.service;

import com.github.worms308.surveyapp.model.Survey;

import java.util.Optional;

public interface SurveyService extends Service<Survey>{
    Optional<Survey> findByName(String name);
}
