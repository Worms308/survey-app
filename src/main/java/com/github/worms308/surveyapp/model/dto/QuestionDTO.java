package com.github.worms308.surveyapp.model.dto;

import lombok.Data;

import java.util.Set;

@Data
public class QuestionDTO {
    private String questionValue;
    private Set<AnswerDTO> answerDTOS;
}
