package com.iciak.surfey.surveyservice.service;

import com.iciak.surfey.surveyservice.entity.SurveyEntity;
import com.iciak.surfey.surveyservice.exception.EntityNotFoundException;
import com.iciak.surfey.surveyservice.model.Survey;
import com.iciak.surfey.surveyservice.repository.SurveyRepository;
import com.iciak.surfey.surveyservice.service.mapper.SurveyMapper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public void create(@NonNull final Survey survey) {
        surveyRepository.save(surveyMapper.createEntity(survey));
    }

    @Transactional
    public void update(@NonNull final UUID uuid, @NonNull final Survey survey) {
        SurveyEntity surveyEntity = surveyRepository.findByUuid(uuid).orElseThrow(
                () -> new EntityNotFoundException("No such a UUID of Survey in the database"));

        surveyEntity.setName(survey.getName());
    }

    public void delete(@NonNull final UUID uuid) {
        surveyRepository.deleteByUuid(uuid);
    }
}