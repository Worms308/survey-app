package com.github.worms308.surveyapp.exception.entity;

import graphql.ErrorType;

public class QuestionNotFoundException extends EntityException {
    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }
}
