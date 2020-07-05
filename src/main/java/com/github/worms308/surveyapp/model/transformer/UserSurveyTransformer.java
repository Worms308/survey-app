package com.github.worms308.surveyapp.model.transformer;

import com.github.worms308.surveyapp.model.UserSurvey;
import com.github.worms308.surveyapp.model.dto.UserSurveyDTO;
import org.springframework.beans.BeanUtils;

import java.util.stream.Collectors;

public class UserSurveyTransformer {
    private UserSurveyTransformer() {
    }

    public static UserSurvey createEntity(UserSurveyDTO userSurveyDTO){
        UserSurvey userSurvey = new UserSurvey();
        userSurvey.setSurvey(SurveyTransformer.createEntity(userSurveyDTO.getSurvey()));
        userSurvey.setChosenAnswers(
                userSurveyDTO.getChosenAnswers()
                .stream()
                .map(ChosenAnswerTransformer::createEntity)
                .collect(Collectors.toSet())
        );

        BeanUtils.copyProperties(userSurveyDTO, userSurvey);
        return userSurvey;
    }

    public static UserSurveyDTO createDTO(UserSurvey userSurvey){
        UserSurveyDTO userSurveyDTO = new UserSurveyDTO();
        userSurveyDTO.setChosenAnswers(
                userSurvey.getChosenAnswers()
                .stream()
                .map(ChosenAnswerTransformer::createDTO)
                .collect(Collectors.toSet())
        );
        userSurveyDTO.setSurvey(SurveyTransformer.createDTO(userSurvey.getSurvey()));

        BeanUtils.copyProperties(userSurvey, userSurveyDTO);
        return userSurveyDTO;
    }
}
