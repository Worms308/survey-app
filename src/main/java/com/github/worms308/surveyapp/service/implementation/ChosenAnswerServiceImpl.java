package com.github.worms308.surveyapp.service.implementation;

import com.github.worms308.surveyapp.model.ChosenAnswer;
import com.github.worms308.surveyapp.model.UserSurvey;
import com.github.worms308.surveyapp.repository.ChosenAnswerRepository;
import com.github.worms308.surveyapp.repository.UserSurveyRepository;
import com.github.worms308.surveyapp.service.ChosenAnswerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service("ChosenAnswerService")
@AllArgsConstructor
public class ChosenAnswerServiceImpl implements ChosenAnswerService {

    private final ChosenAnswerRepository chosenAnswerRepository;
    private final UserSurveyRepository userSurveyRepository;


    @Override
    public Optional<ChosenAnswer> findById(long id) {
        return chosenAnswerRepository.findById(id);
    }

    @Override
    public List<ChosenAnswer> findAll() {
        return chosenAnswerRepository.findAll();
    }

    @Override
    public ChosenAnswer save(ChosenAnswer item) {
        return chosenAnswerRepository.save(item);
    }

    @Override
    public Optional<Set<ChosenAnswer>> findByUserSurveyId(long id) {
        Optional<UserSurvey> userSurvey = userSurveyRepository.findById(id);
        return userSurvey.map(UserSurvey::getChosenAnswers);
    }
}
