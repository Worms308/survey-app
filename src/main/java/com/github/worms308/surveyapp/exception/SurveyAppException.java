package com.github.worms308.surveyapp.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;

public class SurveyAppException extends Exception implements GraphQLError {

    public SurveyAppException() {
    }

    public SurveyAppException(String message) {
        super(message);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.valueOf("Unexpected error");
    }
}
