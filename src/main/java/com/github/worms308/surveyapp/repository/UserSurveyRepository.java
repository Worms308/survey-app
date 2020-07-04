package com.github.worms308.surveyapp.repository;

import com.github.worms308.surveyapp.model.UserSurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSurveyRepository extends JpaRepository<UserSurvey, Long> {
}
