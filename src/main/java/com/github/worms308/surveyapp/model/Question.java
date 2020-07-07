package com.github.worms308.surveyapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @Column(name = "question_value", nullable = false, unique = true)
    private String questionValue;

    @Column(name = "multi_choice", nullable = false)
    private Boolean multiChoice;

    @ManyToMany
    @JoinTable(
            name = "questions_answers",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_id")
    )
    private Set<Answer> answers;

    public Question(String questionValue) {
        this.questionValue = questionValue;
    }
}
