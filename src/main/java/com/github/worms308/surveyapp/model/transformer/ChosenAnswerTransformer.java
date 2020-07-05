package com.github.worms308.surveyapp.model.transformer;

import com.github.worms308.surveyapp.model.ChosenAnswer;
import com.github.worms308.surveyapp.model.dto.ChosenAnswerDTO;
import org.springframework.beans.BeanUtils;

import java.util.Set;
import java.util.stream.Collectors;

public class ChosenAnswerTransformer {

    private ChosenAnswerTransformer() {
    }

    public static ChosenAnswerDTO createDTO(ChosenAnswer chosenAnswers) {
        ChosenAnswerDTO chosenAnswerDTO = new ChosenAnswerDTO();
        chosenAnswerDTO.setAnswerDTOS(
                chosenAnswers.getAnswers()
                .stream()
                .map(AnswerTransformer::createDTO)
                .collect(Collectors.toSet())
        );
        chosenAnswerDTO.setQuestion(QuestionTransformer.createDTO(chosenAnswers.getQuestion()));
        chosenAnswerDTO.setUserSurveyDTO(UserSurveyTransformer.createDTO(chosenAnswers.getUserSurvey()));

        BeanUtils.copyProperties(chosenAnswers, chosenAnswerDTO);
        return chosenAnswerDTO;
    }

    public static ChosenAnswer createEntity(ChosenAnswerDTO chosenAnswerDTO){
        ChosenAnswer chosenAnswer = new ChosenAnswer();
        chosenAnswer.setAnswers(
                chosenAnswerDTO.getAnswerDTOS()
                .stream()
                .map(AnswerTransformer::createEntity)
                .collect(Collectors.toSet())
        );
        chosenAnswer.setQuestion(QuestionTransformer.createEntity(chosenAnswerDTO.getQuestion()));
        chosenAnswer.setUserSurvey(UserSurveyTransformer.createEntity(chosenAnswerDTO.getUserSurveyDTO()));

        BeanUtils.copyProperties(chosenAnswerDTO, chosenAnswer);
        return chosenAnswer;
    }
}
