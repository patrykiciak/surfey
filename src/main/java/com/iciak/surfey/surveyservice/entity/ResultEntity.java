package com.iciak.surfey.surveyservice.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@NoArgsConstructor(access = PRIVATE, force = true)
@AllArgsConstructor(access = PRIVATE)
@Entity(name = "results")
public class ResultEntity {
    @Id
    @GeneratedValue
    private final int id;
    @Column(unique = true, nullable = false)
    @NonNull
    private final UUID uuid;
    @NonNull
    private final UUID userUuid;
    @NonNull
    @ManyToOne
    @JoinColumn //REVIEW: "@JoinColumn(answer_id)
    private AnswerEntity chosenAnswer;
}