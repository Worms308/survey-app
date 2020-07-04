package com.github.worms308.surveyapp;

import com.github.worms308.surveyapp.model.*;
import com.github.worms308.surveyapp.repository.*;
import com.github.worms308.surveyapp.service.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class InitMockDB {

    private final SurveyRepository surveyRepository;
    private final UserSurveyRepository userSurveyRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final ChosenAnswerRepository chosenAnswerRepository;


    @PostConstruct
    public void initDB() {
        initASurvey();
    }

    void initASurvey() {
        Answer tak = initAnswer("Tak");
        Answer nie = initAnswer("Nie");
        Question question1 = initQuestion("Czy masz ukończone 18 lat?", tak, nie);
        Question question2 = initQuestion("Czy umiesz korzystać z komputera?", tak, nie);
        Survey surveyA = initSurvey("Ankieta - A", question1, question2);

        ChosenAnswer chosenAnswer1 = initChosenAnswer(question1, tak);
        ChosenAnswer chosenAnswer2 = initChosenAnswer(question2, nie);
        initUserSurvey(surveyA, chosenAnswer1, chosenAnswer2);
    }

    private UserSurvey initUserSurvey(Survey survey, ChosenAnswer... chosenAnswers) {
        UserSurvey userSurvey = new UserSurvey();
        userSurvey.setSurvey(survey);
        userSurvey.setChosenAnswers(Arrays.stream(chosenAnswers).collect(Collectors.toSet()));
        return userSurveyRepository.save(userSurvey);
    }

    private ChosenAnswer initChosenAnswer(Question question, Answer answer) {
        ChosenAnswer chosenAnswer = new ChosenAnswer();
        chosenAnswer.setAnswers(Collections.singleton(answer));
        chosenAnswer.setQuestion(question);
        return chosenAnswerRepository.save(chosenAnswer);
    }

    private Survey initSurvey(String name, Question... questions) {
        Survey survey = new Survey();
        survey.setName(name);
        survey.setQuestions(Arrays.stream(questions).collect(Collectors.toSet()));
        return surveyRepository.save(survey);
    }

    private Question initQuestion(String question, Answer... answers) {
        Question result = new Question(question);
        result.setAnswers(Arrays.stream(answers).collect(Collectors.toSet()));
        return questionRepository.save(result);
    }

    private Answer initAnswer(String answer) {
        return answerRepository.save(new Answer(answer));
    }

}
