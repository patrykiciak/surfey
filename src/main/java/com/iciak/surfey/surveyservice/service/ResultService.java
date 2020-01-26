package com.iciak.surfey.surveyservice.service;

import com.iciak.surfey.surveyservice.entity.AnswerEntity;
import com.iciak.surfey.surveyservice.entity.ResultEntity;
import com.iciak.surfey.surveyservice.exception.EntityNotFoundException;
import com.iciak.surfey.surveyservice.model.Result;
import com.iciak.surfey.surveyservice.repository.AnswerRepository;
import com.iciak.surfey.surveyservice.repository.ResultRepository;
import com.iciak.surfey.surveyservice.service.mapper.ResultMapper;
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
public class ResultService {
    private final ResultRepository resultRepository;
    private final ResultMapper resultMapper;
    private final AnswerRepository answerRepository;

    public List<Result> findAll() {
        return resultRepository.findAll().stream()
                .map(resultMapper::toModel)
                .collect(toList());
    }

    public Optional<Result> find(@NonNull final UUID uuid) {
        return resultRepository.findByUuid(uuid)
                .map(resultMapper::toModel);
    }

    public void create(@NonNull final Result result) {

        AnswerEntity chosenAnswer = answerRepository.findByUuid(result.getUserUuid()).orElseThrow(
                () -> new EntityNotFoundException("No such a UUID of Answer in the database"));

        resultRepository.save(resultMapper.createResult(result, chosenAnswer));
    }

    @Transactional
    public void update(@NonNull final UUID uuid, @NonNull final Result result) {
        ResultEntity resultEntity = resultRepository.findByUuid(uuid).orElseThrow(
                () -> new EntityNotFoundException("No such a UUID of Result in the database"));

        resultEntity.setChosenAnswer(answerRepository.findByUuid(result.getAnswerUuid())
                .orElseThrow(() -> new EntityNotFoundException("No such a UUID of Answer in the database")));
    }

    public void delete(@NonNull final UUID uuid) {
        resultRepository.deleteByUuid(uuid);
    }
}