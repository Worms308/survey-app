package com.github.worms308.surveyapp.model;

import lombok.*;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_survey")
public class UserSurvey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_survey_id")
    private Long id;
    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @OneToMany
    @JoinColumn(name = "user_survey_id")
    private Set<ChosenAnswer> chosenAnswers;
}
