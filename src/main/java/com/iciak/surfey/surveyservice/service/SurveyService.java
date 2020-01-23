package com.iciak.surfey.surveyservice.service;

import com.iciak.surfey.surveyservice.model.Survey;
import com.iciak.surfey.surveyservice.repository.SurveyRepository;
import com.iciak.surfey.surveyservice.service.converter.SurveyMapper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final SurveyMapper surveyMapper;

    public List<Survey> findAll() {
        return surveyRepository.findAll().stream()
                        .map(surveyMapper::toModel)
                        .collect(toList());
    }

    public Optional<Survey> find(@NonNull final UUID surveyId) {
        return surveyRepository.findByUuid(surveyId)
                .map(surveyMapper::toModel);
    }
}