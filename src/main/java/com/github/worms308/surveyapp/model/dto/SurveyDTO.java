package com.github.worms308.surveyapp.model.dto;

import lombok.Data;

import java.util.Set;

@Data
public class SurveyDTO {

    private String name;
    private Set<QuestionDTO> questionDTOS;
}
