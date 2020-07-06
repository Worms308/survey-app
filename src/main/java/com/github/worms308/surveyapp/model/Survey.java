package com.github.worms308.surveyapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

    @ManyToMany
    private Set<Question> questions;

    @OneToMany
    @JoinColumn(name = "survey_id")
    private Set<UserSurvey> userSurveys;

    public Survey(String name) {
        this.name = name;
    }
}
