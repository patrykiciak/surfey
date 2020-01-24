package com.iciak.surfey.surveyservice.controller;

import com.iciak.surfey.surveyservice.model.Question;
import com.iciak.surfey.surveyservice.model.Questions;
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
}
