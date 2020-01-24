package com.iciak.surfey.surveyservice.controller;

import com.iciak.surfey.surveyservice.exception.EntityNotFoundException;
import com.iciak.surfey.surveyservice.model.Answer;
import com.iciak.surfey.surveyservice.model.Answers;
import com.iciak.surfey.surveyservice.service.AnswerService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import java.util.UUID;

@RestController
@RequestMapping("/answers")
@AllArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping
    public ResponseEntity create(@RequestBody @NonNull final Answer answer) {
        answerService.create(answer);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Answers> getAll() {
       return ResponseEntity.ok(answerService.getAll());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Answer> find(@PathVariable @NonNull final UUID uuid) {
        return answerService.get(uuid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{uuid}")
    public ResponseEntity update(@PathVariable @NonNull final UUID uuid, @RequestBody final Answer answer) {
        answerService.update(uuid, answer);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity delete(@PathVariable @NonNull final UUID uuid) {
        answerService.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}
