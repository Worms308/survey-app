package com.github.worms308.surveyapp.model.transformer;

import com.github.worms308.surveyapp.model.Question;
import com.github.worms308.surveyapp.model.dto.QuestionDTO;
import org.springframework.beans.BeanUtils;

import java.util.stream.Collectors;

public class QuestionTransformer {
    private QuestionTransformer() {
    }

    public static Question createEntity(QuestionDTO questionDTO) {
        Question question = new Question();
        BeanUtils.copyProperties(questionDTO, question);

        question.setAnswers(questionDTO
                .getAnswerDTOS()
                .stream()
                .map(AnswerTransformer::createEntity)
                .collect(Collectors.toSet())
        );
        return question;
    }

    public static QuestionDTO createDTO(Question question) {
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);

        questionDTO.setAnswerDTOS(question
                .getAnswers()
                .stream()
                .map(AnswerTransformer::createDTO)
                .collect(Collectors.toSet())
        );
        return questionDTO;
    }
}
