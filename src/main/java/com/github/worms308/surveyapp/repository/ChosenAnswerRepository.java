package com.github.worms308.surveyapp.repository;

import com.github.worms308.surveyapp.model.ChosenAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChosenAnswerRepository extends JpaRepository<ChosenAnswer, Long> {
    Optional<ChosenAnswer> findByUserSurveyId(long id);
}
