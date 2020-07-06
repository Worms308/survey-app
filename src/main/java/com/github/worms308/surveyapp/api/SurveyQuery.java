package com.github.worms308.surveyapp.api;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.github.worms308.surveyapp.model.ChosenAnswer;
import com.github.worms308.surveyapp.model.Question;
import com.github.worms308.surveyapp.model.Survey;
import com.github.worms308.surveyapp.model.UserSurvey;
import com.github.worms308.surveyapp.model.dto.*;
import com.github.worms308.surveyapp.model.transformer.*;
import com.github.worms308.surveyapp.service.*;
import org.hibernate.Hibernate;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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

    public List<QuestionDTO> getQuestions() {
        return questionService.findAll().stream()
                .map(QuestionTransformer::createDTO)
                .collect(Collectors.toList());
    }

    public List<AnswerDTO> getAnswers() {
        return answerService.findAll().stream()
                .map(AnswerTransformer::createDTO)
                .collect(Collectors.toList());
    }

    public List<ChosenAnswerDTO> getChosenAnswersByUserSurvey(long id){
        Optional<Set<ChosenAnswer>> userSurveyChosenAnswers = chosenAnswerService.findByUserSurveyId(id);
        return userSurveyChosenAnswers.map(chosenAnswers -> chosenAnswers
                .stream()
                .map(ChosenAnswerTransformer::createDTO)
                .collect(Collectors.toList())).orElse(Collections.emptyList());
    }

    public SurveyDTO getSurveyByName(String name){
        Optional<Survey> survey = surveyService.findByName(name);
        return survey.map(SurveyTransformer::createDTO).orElse(null);
    }

    public UserSurveyDTO getUserSurveyById(long id){
        Optional<UserSurvey> userSurvey = userSurveyService.findById(id);
        return userSurvey.map(UserSurveyTransformer::createDTO).orElse(null);
    }

//    public List<Survey> getSurveys() {
//        return surveyService.findAll();
//    }
//
//    public Optional<Survey> getSurveyByName(String name) {
//        return surveyService.findByName(name);
//    }
}
