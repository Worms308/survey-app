package com.github.worms308.surveyapp.api;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.github.worms308.surveyapp.exception.entity.SurveyNotFoundException;
import com.github.worms308.surveyapp.exception.entity.UserSurveyNotFoundException;
import com.github.worms308.surveyapp.model.Survey;
import com.github.worms308.surveyapp.model.UserSurvey;
import com.github.worms308.surveyapp.model.dto.*;
import com.github.worms308.surveyapp.model.transformer.*;
import com.github.worms308.surveyapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("SurveyQuery")
public class SurveyQuery implements GraphQLQueryResolver {

    private final AnswerService answerService;
    private final QuestionService questionService;
    private final ChosenAnswerService chosenAnswerService;
    private final UserSurveyService userSurveyService;
    private final SurveyService surveyService;

    @Autowired
    public SurveyQuery(@Qualifier("AnswerService") AnswerService answerService,
                       @Qualifier("QuestionService") QuestionService questionService,
                       @Qualifier("ChosenAnswerService") ChosenAnswerService chosenAnswerService,
                       @Qualifier("UserSurveyService") UserSurveyService userSurveyService,
                       @Qualifier("SurveyService") SurveyService surveyService) {
        this.answerService = answerService;
        this.questionService = questionService;
        this.chosenAnswerService = chosenAnswerService;
        this.userSurveyService = userSurveyService;
        this.surveyService = surveyService;
    }

    @ResponseStatus(HttpStatus.OK)
    public List<QuestionDTO> getQuestions() {
        return questionService.findAll().stream()
                .map(QuestionTransformer::createDTO)
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    public List<AnswerDTO> getAnswers() {
        return answerService.findAll().stream()
                .map(AnswerTransformer::createDTO)
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    public SurveyDTO getSurveyByName(String name) throws SurveyNotFoundException {
        Optional<Survey> survey = surveyService.findByName(name);
        return survey.map(SurveyTransformer::createDTO).orElseThrow(
                () -> new SurveyNotFoundException("Survey [" + name + "] not found.")
        );
    }

    @ResponseStatus(HttpStatus.OK)
    public UserSurveyDTO getUserSurveyById(long id) throws UserSurveyNotFoundException {
        Optional<UserSurvey> userSurvey = userSurveyService.findById(id);
        return userSurvey.map(UserSurveyTransformer::createDTO).orElseThrow(
                ()-> new UserSurveyNotFoundException("UserSurvey id=[" + id + "] not found.")
        );
    }

}
