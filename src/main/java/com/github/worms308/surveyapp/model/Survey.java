package com.github.worms308.surveyapp.model;

import lombok.*;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "survey")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "survey_id")
    private Long id;
    @Column(name = "name")
    private String name;

    @ManyToMany
    private Set<Question> questions;

    @OneToMany
    private Set<UserSurvey> userSurveys;

    public Survey(String name) {
        this.name = name;
    }
}
