package com.github.worms308.surveyapp.model.transformer;

import com.github.worms308.surveyapp.model.Answer;
import com.github.worms308.surveyapp.model.dto.AnswerDTO;
import org.springframework.beans.BeanUtils;

public class AnswerTransformer {
    private AnswerTransformer() {
    }

    public static Answer createEntity(AnswerDTO answerDTO){
        Answer answer = new Answer();
        BeanUtils.copyProperties(answerDTO, answer);
        return answer;
    }

    public static AnswerDTO createDTO(Answer answer){
        AnswerDTO answerDTO = new AnswerDTO();
        BeanUtils.copyProperties(answer, answerDTO);
        return answerDTO;
    }
}
