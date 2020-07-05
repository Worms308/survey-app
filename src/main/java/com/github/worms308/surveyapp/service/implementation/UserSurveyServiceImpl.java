package com.github.worms308.surveyapp.service.implementation;

import com.github.worms308.surveyapp.model.UserSurvey;
import com.github.worms308.surveyapp.repository.UserSurveyRepository;
import com.github.worms308.surveyapp.service.UserSurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Optional<UserSurvey> findById(long id) {
        return userSurveyRepository.findById(id);
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
