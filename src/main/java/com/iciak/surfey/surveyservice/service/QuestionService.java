package com.iciak.surfey.surveyservice.service;

import com.iciak.surfey.surveyservice.entity.QuestionEntity;
import com.iciak.surfey.surveyservice.entity.SurveyEntity;
import com.iciak.surfey.surveyservice.exception.EntityNotFoundException;
import com.iciak.surfey.surveyservice.model.Question;
import com.iciak.surfey.surveyservice.repository.QuestionRepository;
import com.iciak.surfey.surveyservice.repository.SurveyRepository;
import com.iciak.surfey.surveyservice.service.mapper.AnswerMapper;
import com.iciak.surfey.surveyservice.service.mapper.QuestionMapper;
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
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final SurveyRepository surveyRepository;
    private final AnswerMapper answerMapper;
    private final QuestionMapper questionMapper;

    public void create(@NonNull final Question question) {
        questionRepository.save(QuestionEntity.builder()
                .uuid(UUID.randomUUID())
                .content(question.getContent())
                .survey(Optional.ofNullable(question.getSurveyUuid())
                        .map(uuid -> surveyRepository.findByUuid(uuid).orElseThrow(
                                () -> new EntityNotFoundException("No such a UUID of Survey")
                        ))
                        .orElse(null))
                .answers(question.getAnswers().stream().map(answerMapper::toEntity).collect(toList()))
                .build()
        );
    }

    @Transactional
    public void update(@NonNull final UUID questionUuid, @NonNull final Question question) {
        QuestionEntity questionEntity = questionRepository.findByUuid(questionUuid)
                .orElseThrow(() -> new EntityNotFoundException("No such a UUID of Question in the database"));

        questionEntity.setSurvey(
                Optional.ofNullable(question.getSurveyUuid())
                        .map(uuid -> surveyRepository.findByUuid(question.getSurveyUuid())
                                .orElseThrow(() -> new EntityNotFoundException("No such a UUID of Survey in the database")))
                        .orElse(questionEntity.getSurvey()));

        questionEntity.setContent(question.getContent());
    }

    public void delete(@NonNull final UUID uuid) {
        questionRepository.deleteByUuid(uuid);
    }

    public Optional<Question> find(@NonNull final UUID uuid) {
        return questionRepository.findByUuid(uuid).map(questionMapper::toModel);
    }

    public List<Question> findAll() {
        return questionRepository.findAll().stream().map(questionMapper::toModel).collect(toList());
    }
}
