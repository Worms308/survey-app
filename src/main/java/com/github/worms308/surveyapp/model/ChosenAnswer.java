package com.github.worms308.surveyapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "chosen_answer")
public class ChosenAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chosen_answer_id")
    private Long id;

    @ManyToMany
    private Set<Answer> answers;

    @ManyToOne
    private Question question;

    @ManyToOne
    private UserSurvey userSurvey;
}
