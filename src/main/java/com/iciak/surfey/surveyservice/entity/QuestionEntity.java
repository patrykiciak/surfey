package com.iciak.surfey.surveyservice.entity;

import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.Fetch;

import static javax.persistence.CascadeType.ALL;
import static lombok.AccessLevel.PRIVATE;
import static org.hibernate.annotations.FetchMode.SUBSELECT;

@Data
@Builder
@NoArgsConstructor(access = PRIVATE, force = true)
@AllArgsConstructor(access = PRIVATE)
@Entity(name = "questions")
public class QuestionEntity {

    @Id
    @GeneratedValue //REVIEW: strategy IDENTITY
    private int id;

    @Column(unique = true)
    @NonNull
    private final UUID uuid;

    @NonNull
    @OneToMany(cascade = ALL, orphanRemoval = true)
    @Fetch(SUBSELECT)
    @JoinColumn(nullable = false)
    private final List<AnswerEntity> answers;

    @NonNull
    private String content;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private SurveyEntity survey;
}