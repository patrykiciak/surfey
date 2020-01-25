package com.iciak.surfey.surveyservice.controller;

import com.iciak.surfey.surveyservice.model.Answer;
import com.iciak.surfey.surveyservice.model.Answers;
import com.iciak.surfey.surveyservice.model.Question;
import com.iciak.surfey.surveyservice.model.Questions;
import com.iciak.surfey.surveyservice.service.AnswerService;
import com.iciak.surfey.surveyservice.service.QuestionService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/questions")
@AllArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping
    public ResponseEntity create(@RequestBody @NonNull final Question question) {
        questionService.create(question);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Questions> getAll() {
        return ResponseEntity.ok(Questions.builder()
                .questions(questionService.findAll())
                .build());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Question> get(@PathVariable @NonNull final UUID uuid) {
        return questionService.find(uuid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{uuid}")
    public ResponseEntity update(@PathVariable @NonNull final UUID uuid, @RequestBody @NonNull final Question question) {
        questionService.update(uuid, question);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity delete(@PathVariable @NonNull final UUID uuid) {
        questionService.delete(uuid);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{questionUuid}/answers")
    public ResponseEntity createAnswer(@PathVariable @NonNull UUID questionUuid, @RequestBody @NonNull final Answer answer) {
        answerService.create(questionUuid, answer);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{questionUuid}/answers")
    public ResponseEntity<Answers> getAllAnswers(@PathVariable @NonNull UUID questionUuid) {
        return ResponseEntity.ok(answerService.getAll(questionUuid));
    }

    @GetMapping("/{questionUuid}/answers/{answerUuid}")
    public ResponseEntity<Answer> findAnswer(@PathVariable @NonNull final UUID questionUuid,
                                             @PathVariable @NonNull final UUID answerUuid) {
        return answerService.get(questionUuid, answerUuid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{questionUuid}/answers/{answerUuid}")
    public ResponseEntity updateQuestion(@PathVariable @NonNull final UUID questionUuid,
                                         @PathVariable @NonNull final UUID answerUuid,
                                         @RequestBody final Answer answer) {
        answerService.updateAnswer(questionUuid, answerUuid, answer);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{questionUuid}/answers/{answerUuid}")
    public ResponseEntity deleteQuestion(@PathVariable @NonNull final UUID questionUuid,
                                         @PathVariable @NonNull final UUID answerUuid) {
        answerService.deleteAnswer(questionUuid, answerUuid);
        return ResponseEntity.noContent().build();
    }
}
