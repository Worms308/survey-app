package com.github.worms308.surveyapp.exception.entity;

import graphql.ErrorType;

public class SurveyNotFoundException extends EntityException {

    public SurveyNotFoundException() {
    }

    public SurveyNotFoundException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }
}
