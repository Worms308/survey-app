package com.github.worms308.surveyapp.service.implementation;

import com.github.worms308.surveyapp.model.Answer;
import com.github.worms308.surveyapp.model.Question;
import com.github.worms308.surveyapp.repository.AnswerRepository;
import com.github.worms308.surveyapp.repository.QuestionRepository;
import com.github.worms308.surveyapp.service.QuestionService;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("QuestionService")
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

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
    public Question save(Question item) {
        Set<Answer> answers = item.getAnswers();
        item.setAnswers(saveOrUpdateAnswers(answers));
        return questionRepository.save(item);
    }

    private Set<Answer> saveOrUpdateAnswers(Set<Answer> answers){
        return answers.stream().map(answer -> {
            Optional<Answer> answerFromRepo = answerRepository.findByValue(answer.getValue());
            return answerFromRepo.orElseGet(() -> answerRepository.save(answer));
        }).collect(Collectors.toSet());
    }
}
