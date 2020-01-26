package com.iciak.surfey.surveyservice.service;

import com.iciak.surfey.surveyservice.entity.AnswerEntity;
import com.iciak.surfey.surveyservice.entity.QuestionEntity;
import com.iciak.surfey.surveyservice.exception.EntityNotFoundException;
import com.iciak.surfey.surveyservice.model.Answer;
import com.iciak.surfey.surveyservice.model.Answers;
import com.iciak.surfey.surveyservice.repository.QuestionRepository;
import com.iciak.surfey.surveyservice.service.mapper.AnswerMapper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class AnswerService {
    private final AnswerMapper answerMapper;
    private final QuestionRepository questionRepository;

    public Answers findAll(@NonNull final UUID questionUuid) {
        return Answers.builder()
                .answers(questionRepository.findByUuid(questionUuid)
                        .orElseThrow(() -> new EntityNotFoundException("No such a UUID of Question in the database"))
                        .getAnswers()
                        .stream().map(answerMapper::toModel).collect(toList()))
                .build();
    }

    @Transactional
    public void createAnswer(@NonNull final UUID questionUuid, @NonNull final Answer answer) {
        QuestionEntity questionEntity = questionRepository.findByUuid(questionUuid).orElseThrow(
                () -> new EntityNotFoundException("No such a UUID of Question in the database")
        );
        questionEntity.getAnswers().add(answerMapper.createEntity(answer));
    }

    public Optional<Answer> findAnswer(@NonNull final UUID questionUuid, @NonNull final UUID answerUuid) {
        QuestionEntity questionEntity = questionRepository.findByUuid(questionUuid).orElseThrow(
                () -> new EntityNotFoundException("No such a UUID of Question in the database")
        );
        return questionEntity.getAnswers()
                .stream()
                .filter(x -> x.getUuid().equals(answerUuid))
                .findFirst()
                .map(answerMapper::toModel);
    }

    @Transactional
    public void updateAnswer(@NonNull final UUID questionUuid, @NonNull final UUID answerUuid, @NonNull final Answer answer) {
        QuestionEntity questionEntity = questionRepository.findByUuid(questionUuid).orElseThrow(
                () -> new EntityNotFoundException("No such a UUID of Question in the database")
        );

        AnswerEntity dbAnswer = questionEntity.getAnswers().stream()
                .filter(x -> x.getUuid().equals(answerUuid))
                .findFirst().orElseThrow(() -> new EntityNotFoundException("No such a UUID of Answer in the database"));

        dbAnswer.setContent(answer.getContent());
    }

    @Transactional
    public void deleteAnswer(@NonNull final UUID questionUuid, @NonNull final UUID answerUuid) {
        QuestionEntity questionEntity = questionRepository.findByUuid(questionUuid).orElseThrow(
                () -> new EntityNotFoundException("No such a UUID of Question in the database")
        );

        AnswerEntity answerEntity = questionEntity.getAnswers()
                .stream()
                .filter(x -> x.getUuid().equals(answerUuid))
                .findFirst().orElseThrow(() -> new EntityNotFoundException("No such a UUID of Answer in the database"));

        questionEntity.getAnswers().remove(answerEntity);
    }
}