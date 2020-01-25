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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class AnswerService {
    private final AnswerMapper answerMapper;
    private final QuestionRepository questionRepository;

    public Answers getAll(@NonNull final UUID questionUuid) {
        return Answers.builder()
                .answers(questionRepository.findByUuid(questionUuid).orElseThrow(
                        () -> new EntityNotFoundException("No such a UUID of Question in the database")
                ).getAnswers()
                        .stream().map(answerMapper::toModel).collect(toList()))
                .build();
    }

    public void create(@NonNull final UUID questionUuid, @NonNull final Answer answer) {
        QuestionEntity questionEntity = questionRepository.findByUuid(questionUuid).orElseThrow(
                () -> new EntityNotFoundException("No such a UUID of Question in the database")
        );
        questionEntity.getAnswers().add(AnswerEntity.builder()
                .uuid(UUID.randomUUID())
                .content(answer.getContent())
                .build());
        questionRepository.save(questionEntity);
    }

    public Optional<Answer> get(@NonNull final UUID questionUuid, @NonNull final UUID answerUuid) {
        QuestionEntity questionEntity = questionRepository.findByUuid(questionUuid).orElseThrow(
                () -> new EntityNotFoundException("No such a UUID of Question in the database")
        );
        return questionEntity.getAnswers()
                .stream()
                .filter(x -> x.getUuid().equals(answerUuid))
                .findFirst()
                .map(answerMapper::toModel);
    }

    public void updateAnswer(@NonNull final UUID questionUuid, @NonNull final UUID answerUuid, @NonNull final Answer answer) {
        QuestionEntity questionEntity = questionRepository.findByUuid(questionUuid).orElseThrow(
                () -> new EntityNotFoundException("No such a UUID of Question in the database")
        );
        List<AnswerEntity> dbAnswers = questionEntity.getAnswers();
        AnswerEntity oldAnswer = dbAnswers.stream()
                .filter(x -> x.getUuid().equals(answerUuid))
                .findFirst().orElseThrow(
                        () -> new EntityNotFoundException("No such a UUID of Answer in the database")
                );
        oldAnswer.setContent(answer.getContent());
        questionRepository.save(questionEntity);
    }

    public void deleteAnswer(@NonNull final UUID questionUuid, @NonNull final UUID answerUuid) {
        QuestionEntity questionEntity = questionRepository.findByUuid(questionUuid).orElseThrow(
                () -> new EntityNotFoundException("No such a UUID of Question in the database")
        );

        AnswerEntity answerEntity = questionEntity.getAnswers()
                .stream()
                .filter(x -> x.getUuid().equals(answerUuid))
                .findFirst().orElseThrow(
                        () -> new EntityNotFoundException("No such a UUID of Answer in the database")
                );

        questionEntity.getAnswers().remove(answerEntity);

        questionRepository.save(questionEntity);
    }
}
