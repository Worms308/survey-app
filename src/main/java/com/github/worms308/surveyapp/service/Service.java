package com.github.worms308.surveyapp.service;

import com.github.worms308.surveyapp.model.Question;

import java.util.List;
import java.util.Optional;

public interface Service <T> {
    Optional<T> findById(long id);
    List<T> findAll();
    T add(T item);
}
