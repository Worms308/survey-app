package com.github.worms308.surveyapp.model.transformer;

import com.github.worms308.surveyapp.model.Survey;
import com.github.worms308.surveyapp.model.dto.SurveyDTO;
import org.springframework.beans.BeanUtils;

import java.util.stream.Collectors;

public class SurveyTransformer {
    private SurveyTransformer() {
    }

    public static Survey createEntity(SurveyDTO surveyDTO){
        Survey survey = new Survey();
        survey.setQuestions(
                surveyDTO.getQuestionDTOS()
                .stream()
                .map(QuestionTransformer::createEntity)
                .collect(Collectors.toSet())
        );

        BeanUtils.copyProperties(surveyDTO, survey);
        return survey;
    }

    public static SurveyDTO createDTO(Survey survey){
        SurveyDTO surveyDTO = new SurveyDTO();
        surveyDTO.setQuestionDTOS(
                survey.getQuestions()
                .stream()
                .map(QuestionTransformer::createDTO)
                .collect(Collectors.toSet())
        );

        BeanUtils.copyProperties(survey, surveyDTO);
        return surveyDTO;
    }

}
