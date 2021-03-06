package com.github.worms308.surveyapp.service.implementation;

import com.github.worms308.surveyapp.model.Question;
import com.github.worms308.surveyapp.model.Survey;
import com.github.worms308.surveyapp.repository.SurveyRepository;
import com.github.worms308.surveyapp.service.QuestionService;
import com.github.worms308.surveyapp.service.SurveyService;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service("SurveyService")
@AllArgsConstructor
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;
    private final QuestionService questionService;

    @Override
    public Optional<Survey> findById(long id) {
        return surveyRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Survey> findAll() {
        List<Survey> surveys = surveyRepository.findAll();
        surveys.forEach(this::initializeSurvey);
        return surveys;
    }

    @Override
    public Survey save(Survey item) {
        item.setQuestions(saveOrUpdateQuestions(item.getQuestions()));
        return surveyRepository.save(item);
    }

    private Set<Question> saveOrUpdateQuestions(Set<Question> questions){
        return questions.stream()
                .map(questionService::save)
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Survey> findByName(String name) {
        Optional<Survey> surveyOptional = surveyRepository.findByName(name);
        surveyOptional.ifPresent(this::initializeSurvey);
        return surveyOptional;
    }

    private void initializeSurvey(Survey survey){
        Hibernate.initialize(survey.getQuestions());
        survey.getQuestions().forEach(question -> Hibernate.initialize(question.getAnswers()));
    }
}
