package com.github.worms308.surveyapp.api;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.github.worms308.surveyapp.model.Answer;
import com.github.worms308.surveyapp.model.Question;
import com.github.worms308.surveyapp.model.Survey;
import com.github.worms308.surveyapp.model.dto.AnswerDTO;
import com.github.worms308.surveyapp.model.dto.QuestionDTO;
import com.github.worms308.surveyapp.model.dto.SurveyDTO;
import com.github.worms308.surveyapp.model.transformer.AnswerTransformer;
import com.github.worms308.surveyapp.model.transformer.QuestionTransformer;
import com.github.worms308.surveyapp.model.transformer.SurveyTransformer;
import com.github.worms308.surveyapp.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SurveyMutation implements GraphQLMutationResolver {

    private final AnswerService answerService;
    private final QuestionService questionService;
    private final ChosenAnswerService chosenAnswerService;
    private final UserSurveyService userSurveyService;
    private final SurveyService surveyService;

    @Autowired
    public SurveyMutation(@Qualifier("AnswerService") AnswerService answerService,
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

    public AnswerDTO createAnswer(AnswerDTO answerDTO) {
        Answer answer = answerService.save(
                AnswerTransformer.createEntity(answerDTO)
        );
        return AnswerTransformer.createDTO(answer);
    }

    public QuestionDTO createQuestion(QuestionDTO questionDTO){
        Question question = questionService.save(
                QuestionTransformer.createEntity(questionDTO)
        );
        return QuestionTransformer.createDTO(question);
    }

    public SurveyDTO createSurvey(SurveyDTO surveyDTO){
        Survey survey = surveyService.save(
                SurveyTransformer.createEntity(surveyDTO)
        );
        return SurveyTransformer.createDTO(survey);
    }
}
