package com.iciak.surfey.surveyservice.service;

import com.iciak.surfey.surveyservice.entity.AnswerEntity;
import com.iciak.surfey.surveyservice.entity.ResultEntity;
import com.iciak.surfey.surveyservice.exception.EntityNotFoundException;
import com.iciak.surfey.surveyservice.model.Result;
import com.iciak.surfey.surveyservice.repository.AnswerRepository;
import com.iciak.surfey.surveyservice.repository.ResultRepository;
import com.iciak.surfey.surveyservice.service.converter.ResultMapper;
import com.iciak.surfey.userservice.repository.UserRepository;
import com.iciak.surfey.userservice.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class ResultService {
    private final ResultRepository repository;
    private final UserRepository userRepository;
    private final ResultMapper mapper;
    private final UserMapper userMapper;
    private final AnswerRepository answerRepository;

    public List<Result> findAll() {
        return repository.findAll().stream()
                .map(mapper::toModel)
                .collect(toList());
    }

    public Optional<Result> find(UUID uuid) {
        return repository.findByUuid(uuid)
                .map(mapper::toModel);
    }

    public void create(Result result) {

        repository.save(ResultEntity.builder()
                .chosenAnswer(answerRepository.findByUuid(result.getChosenAnswer().getUuid()).orElseThrow(EntityNotFoundException::new))
                .uuid(UUID.randomUUID())
                .user(userRepository.findByUuid(result.getUser().getUuid()).orElseThrow())
                .build());
    }

    public void update(UUID uuid, Result result) {
        ResultEntity dbQuestion = repository.findByUuid(uuid).orElseThrow(EntityNotFoundException::new);
        repository.save(ResultEntity.builder()
                .user(userMapper.toEntity(result.getUser()))
                .id(dbQuestion.getId())
                .uuid(result.getUuid())
                .chosenAnswer(AnswerEntity.builder().uuid(result.getChosenAnswer().getUuid()).build())
                .build()
        );
    }

    public void delete(final UUID uuid) {
        repository.deleteByUuid(uuid);
    }
}