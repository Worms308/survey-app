package com.github.worms308.surveyapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "chosen_answer")
public class ChosenAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chosen_answer_id")
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "answers_chosen_answers",
            joinColumns = @JoinColumn(name = "chosen_answer_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_id")
    )
    private Set<Answer> answers;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "user_survey_id")
    private UserSurvey userSurvey;
}
