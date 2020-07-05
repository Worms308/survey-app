package com.github.worms308.surveyapp.model.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ChosenAnswerDTO {

    private QuestionDTO question;
    private Set<AnswerDTO> answerDTOS;
    private UserSurveyDTO userSurveyDTO;
}
