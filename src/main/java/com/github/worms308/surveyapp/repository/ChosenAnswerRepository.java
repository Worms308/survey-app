package com.github.worms308.surveyapp.repository;

import com.github.worms308.surveyapp.model.ChosenAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChosenAnswerRepository extends JpaRepository<ChosenAnswer, Long> {

//    @Query("SELECT US FROM UserSurvey US JOIN ChosenAnswer CA ON (US.id = CA.userSurvey.id) WHERE US.id = ?1")
    Optional<ChosenAnswer> findByUserSurveyId(long id);
}
