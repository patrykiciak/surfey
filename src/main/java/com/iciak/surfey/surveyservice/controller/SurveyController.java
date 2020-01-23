package com.iciak.surfey.surveyservice.controller;

import com.iciak.surfey.surveyservice.model.Survey;
import com.iciak.surfey.surveyservice.model.Surveys;
import com.iciak.surfey.surveyservice.service.SurveyService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/surveys")
@AllArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;

    @GetMapping
    public ResponseEntity<Surveys> getSurveys() {
        return ResponseEntity.ok(
                Surveys.builder().surveys(surveyService.findAll()).build()
        );
    }

    @GetMapping("/{surveyId}")
    public ResponseEntity<Survey> findSurvey(@PathVariable @NonNull final UUID surveyId) {
        return surveyService.find(surveyId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}