package com.github.worms308.surveyapp.service.implementation;

import com.github.worms308.surveyapp.model.UserSurvey;
import com.github.worms308.surveyapp.repository.UserSurveyRepository;
import com.github.worms308.surveyapp.service.UserSurveyService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("UserSurveyService")
public class UserSurveyServiceImpl implements UserSurveyService {

    private final UserSurveyRepository userSurveyRepository;

    @Autowired
    public UserSurveyServiceImpl(UserSurveyRepository userSurveyRepository) {
        this.userSurveyRepository = userSurveyRepository;
    }

    @Override
    @Transactional
    public Optional<UserSurvey> findById(long id) {
        Optional<UserSurvey> userSurvey = userSurveyRepository.findById(id);
        userSurvey.ifPresent(Hibernate::initialize);
        userSurvey.ifPresent(survey -> Hibernate.initialize(survey.getSurvey()));
        userSurvey.ifPresent(survey -> Hibernate.initialize(survey.getSurvey().getQuestions()));
        userSurvey.ifPresent(survey -> Hibernate.initialize(survey.getChosenAnswers()));
        userSurvey.ifPresent(userSurveyLocal -> Hibernate.initialize(userSurveyLocal.getChosenAnswers()));
        userSurvey.ifPresent(userSurveyLocal -> userSurveyLocal
                .getChosenAnswers()
                .forEach(chosenAnswer -> Hibernate.initialize(chosenAnswer.getAnswers())));
        userSurvey.ifPresent(userSurveyLocal -> userSurveyLocal
                .getChosenAnswers()
                .forEach(chosenAnswer -> Hibernate.initialize(chosenAnswer.getQuestion())));
        userSurvey.ifPresent(userSurveyLocal -> userSurveyLocal
                .getChosenAnswers()
                .forEach(chosenAnswer -> Hibernate.initialize(chosenAnswer.getQuestion().getAnswers())));
        return userSurvey;
    }

    @Override
    public List<UserSurvey> findAll() {
        return userSurveyRepository.findAll();
    }

    @Override
    public UserSurvey save(UserSurvey item) {
        return userSurveyRepository.save(item);
    }
}
