package com.iciak.surfey.surveyservice.controller;

import com.iciak.surfey.surveyservice.model.QuestionResponse;
import com.iciak.surfey.surveyservice.model.QuestionResponses;
import com.iciak.surfey.surveyservice.service.QuestionResponseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/question-responses")
public class QuestionResponseController {
    private final QuestionResponseService questionResponseService;

    @GetMapping
    public ResponseEntity<QuestionResponses> findAll() {
        return ResponseEntity.ok(
                QuestionResponses.builder()
                        .questionResponses(questionResponseService.findAll())
                        .build()
        );
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<QuestionResponse> find(@PathVariable final UUID uuid) {
        return questionResponseService.find(uuid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity create(@RequestBody final QuestionResponse questionResponse) {
        questionResponseService.create(questionResponse);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity update(@RequestBody final QuestionResponse questionResponse) {
        questionResponseService.update(questionResponse);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity delete(@PathVariable final UUID uuid) {
        questionResponseService.delete(uuid);
        return ResponseEntity.ok().build();
    }
}
