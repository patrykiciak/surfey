package com.iciak.surfey.surveyservice.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.*;
import static lombok.AccessLevel.PRIVATE;
import static org.hibernate.annotations.FetchMode.SUBSELECT;

@Data
@Builder
@NoArgsConstructor(access = PRIVATE, force = true)
@AllArgsConstructor(access = PRIVATE)
@Entity(name = "questions")
public class QuestionEntity {

    @Id
    @GeneratedValue
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