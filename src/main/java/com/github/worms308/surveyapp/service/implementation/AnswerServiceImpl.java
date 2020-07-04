package com.github.worms308.surveyapp.service.implementation;

import com.github.worms308.surveyapp.model.Answer;
import com.github.worms308.surveyapp.repository.AnswerRepository;
import com.github.worms308.surveyapp.service.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    @Override
    public Optional<Answer> findById(long id) {
        return answerRepository.findById(id);
    }

    @Override
    public List<Answer> findAll() {
        return answerRepository.findAll();
    }

    @Override
    public Answer add(Answer answer) {
        return answerRepository.save(answer);
    }
}
