package com.github.worms308.surveyapp.model.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ChosenAnswerDTO {

    private QuestionDTO questionDTO;
    private Set<AnswerDTO> answerDTOS;
}
