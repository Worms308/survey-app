package com.github.worms308.surveyapp.api;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.github.worms308.surveyapp.model.Answer;
import com.github.worms308.surveyapp.model.Question;
import com.github.worms308.surveyapp.service.AnswerService;
import com.github.worms308.surveyapp.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SurveyQuery implements GraphQLQueryResolver {

    private final AnswerService answerService;
    private final QuestionService questionService;

    @Autowired
    public SurveyQuery(AnswerService answerService, @Qualifier("QuestionService") QuestionService questionService) {
        this.answerService = answerService;
        this.questionService = questionService;
    }

    public List<Question> getQuestions() {
        return questionService.findAll();
    }

    public List<Answer> getAnswers(){
        return answerService.findAll();
    }
}
