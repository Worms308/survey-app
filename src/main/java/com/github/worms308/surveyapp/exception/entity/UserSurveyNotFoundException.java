package com.github.worms308.surveyapp.exception.entity;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class UserSurveyNotFoundException extends EntityException {

    {
        this.setHttpStatusCode(HttpStatus.NOT_FOUND);
    }

    public UserSurveyNotFoundException() {
    }

    public UserSurveyNotFoundException(String message) {
        super(message);
    }

}
