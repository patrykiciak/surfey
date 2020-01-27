package com.iciak.surfey.surveyservice.controller;

import java.util.UUID;

import com.iciak.surfey.surveyservice.model.Survey;
import com.iciak.surfey.surveyservice.model.Surveys;
import com.iciak.surfey.surveyservice.service.SurveyService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping
    //REVIEW: it can return void
    //REVIEW: use Annotation @ResponseStatus(NO_CONTENT)
    public ResponseEntity createSurvey(@RequestBody @NonNull final Survey survey) {
        surveyService.create(survey);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{uuid}")
    //REVIEW: it can return void
    //REVIEW: use Annotation @ResponseStatus(NO_CONTENT)
    public ResponseEntity updateSurvey(@PathVariable @NonNull final UUID uuid, @RequestBody @NonNull final Survey survey) {
        surveyService.update(uuid, survey);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{uuid}")
    //REVIEW: it can return void
    //REVIEW: use Annotation @ResponseStatus(NO_CONTENT)
    public ResponseEntity deleteSurvey(@PathVariable @NonNull final UUID uuid) {
        surveyService.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}