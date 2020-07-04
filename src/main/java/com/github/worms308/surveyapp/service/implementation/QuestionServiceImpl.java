package com.github.worms308.surveyapp.service.implementation;

import com.github.worms308.surveyapp.model.Question;
import com.github.worms308.surveyapp.repository.QuestionRepository;
import com.github.worms308.surveyapp.service.QuestionService;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("QuestionService")
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Override
    public Optional<Question> findById(long id) {
        return questionRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Question> findAll() {
        List<Question> questions = questionRepository.findAll();
        questions.forEach(question -> Hibernate.initialize(question.getAnswers()));
        return questions;
    }

    @Override
    public Question add(Question item) {
        return questionRepository.save(item);
    }
}
