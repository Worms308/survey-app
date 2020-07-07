package com.github.worms308.surveyapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "survey")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "survey_id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", length = 4000)
    private String description;

    @ManyToMany
    private Set<Question> questions;

    @OneToMany
    @JoinColumn(name = "survey_id")
    private Set<UserSurvey> userSurveys;
}
