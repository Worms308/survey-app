package com.github.worms308.surveyapp.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long id;
    @Column(name = "value", nullable = false, unique = true)
    private String value;

    public Answer(String value) {
        this.value = value;
    }
}
