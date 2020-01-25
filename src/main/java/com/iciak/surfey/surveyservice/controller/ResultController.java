package com.iciak.surfey.surveyservice.controller;

import com.iciak.surfey.surveyservice.model.Result;
import com.iciak.surfey.surveyservice.model.Results;
import com.iciak.surfey.surveyservice.service.ResultService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/results")
public class ResultController {
    private final ResultService resultService;

    @GetMapping
    public ResponseEntity<Results> findAll() {
        return ResponseEntity.ok(
                Results.builder()
                        .results(resultService.findAll())
                        .build()
        );
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Result> find(@PathVariable @NonNull final UUID uuid) {
        return resultService.find(uuid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity create(@RequestBody @NonNull final Result result) {
        resultService.create(result);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{uuid}")
    public ResponseEntity update(@PathVariable @NonNull final UUID uuid, @RequestBody @NonNull final Result result) {
        resultService.update(uuid, result);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity delete(@PathVariable @NonNull final UUID uuid) {
        resultService.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}
