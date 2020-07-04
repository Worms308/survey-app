package com.github.worms308.surveyapp.service.implementation;

import com.github.worms308.surveyapp.model.ChosenAnswer;
import com.github.worms308.surveyapp.repository.ChosenAnswerRepository;
import com.github.worms308.surveyapp.service.ChosenAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("ChosenAnswerService")
public class ChosenAnswerServiceImpl implements ChosenAnswerService {

    private final ChosenAnswerRepository chosenAnswerRepository;

    @Autowired
    public ChosenAnswerServiceImpl(ChosenAnswerRepository chosenAnswerRepository) {
        this.chosenAnswerRepository = chosenAnswerRepository;
    }

    @Override
    public Optional<ChosenAnswer> findById(long id) {
        return chosenAnswerRepository.findById(id);
    }

    @Override
    public List<ChosenAnswer> findAll() {
        return chosenAnswerRepository.findAll();
    }

    @Override
    public ChosenAnswer add(ChosenAnswer item) {
        return chosenAnswerRepository.save(item);
    }
}
