package com.iciak.surfey.surveyservice.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.PERSIST;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;
import static org.hibernate.annotations.FetchMode.SUBSELECT;

@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE, force = true)
@Data
@Builder
@Entity(name = "surveys")
public class SurveyEntity {
    @Id
    @GeneratedValue
    private final int id;
    @Column(unique = true)
    @NonNull
    private final UUID uuid;
    @NonNull
    private final String name;
    @OneToMany(cascade = ALL)
    @Fetch(SUBSELECT)
    @JoinColumn(name = "survey_id")
    @NonNull
    private final List<QuestionEntity> questions;
}