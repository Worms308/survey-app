package com.github.worms308.surveyapp.model.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserSurveyDTO {

    private SurveyDTO survey;
    private Set<ChosenAnswerDTO> chosenAnswers;
}
