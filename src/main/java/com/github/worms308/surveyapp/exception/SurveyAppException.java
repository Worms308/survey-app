package com.github.worms308.surveyapp.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class SurveyAppException extends Exception implements GraphQLError {

    private HttpStatus httpStatusCode;

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
        return ErrorType.DataFetchingException;
    }

    @Override
    public Map<String, Object> getExtensions() {
        Map<String, Object> extensions = new HashMap<>();
        extensions.put("code", this.getHttpStatusCode());
        extensions.put("timestamp", LocalDateTime.now().toString());
        return extensions;
    }
}
