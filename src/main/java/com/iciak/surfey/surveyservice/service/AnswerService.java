package com.iciak.surfey.surveyservice.service;

import com.iciak.surfey.surveyservice.entity.AnswerEntity;
import com.iciak.surfey.surveyservice.entity.QuestionEntity;
import com.iciak.surfey.surveyservice.exception.EntityNotFoundException;
import com.iciak.surfey.surveyservice.model.Answer;
import com.iciak.surfey.surveyservice.model.Answers;
import com.iciak.surfey.surveyservice.repository.AnswerRepository;
import com.iciak.surfey.surveyservice.service.converter.AnswerMapper;
import com.iciak.surfey.surveyservice.service.converter.QuestionMapper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;
    private final QuestionMapper questionMapper;

    public Answers getAll() {
        return Answers.builder()
                .answers(answerRepository.findAll().stream().map(answerMapper::toModel).collect(toList()))
                .build();
    }

    public void create(@NonNull final Answer answer) {
        answerRepository.save(AnswerEntity.builder()
                .uuid(UUID.randomUUID())
                .content(answer.getContent())
                .build());
    }

    public Optional<Answer> get(@NonNull final UUID uuid) {
        return answerRepository.findByUuid(uuid).map(answerMapper::toModel);
    }

    public void update(@NonNull final UUID uuid, @NonNull final Answer answer) {
        answerRepository.save(AnswerEntity.builder()
                .id(answerRepository.findByUuid(uuid).orElseThrow(EntityNotFoundException::new).getId())
                .uuid(uuid)
                .content(answer.getContent())
                .build());
    }

    public void delete(@NonNull final UUID uuid) {
        answerRepository.deleteByUuid(uuid);
    }
}
