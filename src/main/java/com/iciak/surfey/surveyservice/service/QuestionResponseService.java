package com.iciak.surfey.surveyservice.service;

import com.iciak.surfey.surveyservice.entity.AnswerOptionEntity;
import com.iciak.surfey.surveyservice.entity.QuestionResponseEntity;
import com.iciak.surfey.surveyservice.model.AnswerOption;
import com.iciak.surfey.surveyservice.model.QuestionResponse;
import com.iciak.surfey.surveyservice.repository.AnswerOptionRepository;
import com.iciak.surfey.surveyservice.repository.QuestionResponseRepository;
import com.iciak.surfey.surveyservice.repository.SurveyRepository;
import com.iciak.surfey.surveyservice.service.converter.AnswerOptionMapper;
import com.iciak.surfey.surveyservice.service.converter.QuestionResponseMapper;
import com.iciak.surfey.userservice.entity.UserEntity;
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
public class QuestionResponseService {
    private final QuestionResponseRepository repository;
    private final UserRepository userRepository;
    private final QuestionResponseMapper mapper;
    private final UserMapper userMapper;
    private final AnswerOptionRepository answerOptionRepository;

    public List<QuestionResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toModel)
                .collect(toList());
    }

    public Optional<QuestionResponse> find(UUID uuid) {
        return repository.findByUuid(uuid)
                .map(mapper::toModel);
    }

    public void create(QuestionResponse questionResponse) {

        repository.save(QuestionResponseEntity.builder()
                .chosenAnswer(answerOptionRepository.findByUuid(questionResponse.getChosenAnswer().getUuid()).orElseThrow())
                .uuid(UUID.randomUUID())
                .user(userRepository.findByUuid(questionResponse.getUser().getUuid()).orElseThrow())
                .build());
    }

    public void update(QuestionResponse questionResponse) {
        QuestionResponseEntity dbQuestion = repository.findByUuid(questionResponse.getUuid()).orElseThrow();
        repository.save(QuestionResponseEntity.builder()
                .user(userMapper.toEntity(questionResponse.getUser()))
                .id(dbQuestion.getId())
                .uuid(questionResponse.getUuid())
                .chosenAnswer(AnswerOptionEntity.builder().uuid(questionResponse.getChosenAnswer().getUuid()).build())
                .build()
        );
    }

    public void delete(final UUID uuid) {
        repository.deleteByUuid(uuid);
    }
}