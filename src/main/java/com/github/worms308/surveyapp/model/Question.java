package com.github.worms308.surveyapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;

    @ManyToMany
    @JoinTable(
            name = "questions_answers",
            joinColumns = @JoinColumn(name = "questions_id"),
            inverseJoinColumns = @JoinColumn(name = "answers_id")
    )
    private Set<Answer> answers;


    public Question(String value) {
        this.value = value;
    }
}
