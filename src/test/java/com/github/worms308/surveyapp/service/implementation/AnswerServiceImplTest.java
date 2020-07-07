package com.github.worms308.surveyapp.service.implementation;

import com.github.worms308.surveyapp.model.Answer;
import com.github.worms308.surveyapp.repository.AnswerRepository;
import com.github.worms308.surveyapp.service.AnswerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class AnswerServiceImplTest {

    @Autowired
    private AnswerService answerService;
    @Autowired
    private AnswerRepository answerRepository;

    @BeforeEach
    void setUp(){
        this.cleanUpAnswerRepository();
        this.addTwoAnswersToRepository();
    }

    @Test
    void shouldFindAnswers(){
        assertEquals(2, answerService.findAll().size());
    }

    @Test
    void shouldGetValuesOfAnswers(){
        List<Answer> allAnswers = answerService.findAll();
        Optional<Answer> optionalYes = allAnswers.stream()
                .filter(answer -> "Yes".equalsIgnoreCase(answer.getAnswerValue()))
                .findFirst();
        Optional<Answer> optionalNo = allAnswers.stream()
                .filter(answer -> "No".equalsIgnoreCase(answer.getAnswerValue()))
                .findFirst();

        assertTrue(optionalYes.isPresent() && optionalNo.isPresent());
    }

    private void addTwoAnswersToRepository(){
        Answer answerYes = new Answer();
        Answer answerNo = new Answer();
        answerYes.setAnswerValue("Yes");
        answerNo.setAnswerValue("No");

        answerRepository.save(answerYes);
        answerRepository.save(answerNo);
    }

    private void cleanUpAnswerRepository(){
        answerRepository.deleteAll();
    }
}
