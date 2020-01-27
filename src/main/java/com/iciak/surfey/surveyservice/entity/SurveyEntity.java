package com.iciak.surfey.surveyservice.entity;

import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.Fetch;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import static lombok.AccessLevel.PRIVATE;
import static org.hibernate.annotations.FetchMode.SUBSELECT;

@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE, force = true)
@Data
@Builder
@Entity(name = "surveys")
public class SurveyEntity {
    @Id
    @GeneratedValue //REVIEW: strategy IDENTITY
    private final int id;
    @Column(unique = true)
    @NonNull
    private final UUID uuid;
    @NonNull
    private String name;
    @OneToMany(cascade = {DETACH, PERSIST, REFRESH, MERGE}, mappedBy = "survey")
    @Fetch(SUBSELECT)
    @NonNull
    private final List<QuestionEntity> questions;

    @PrePersist @PreUpdate
    private void setBothDirectionAssociation() {
        //REVIEW: 1. Nie uzywaj czegos takiego jak assert, to gowno, lepiej sthrowuj exception jakis dedykowany
        //REVIEW: 2. Ale w tym przypadku questions nie bedzie nullem bo masz @NonNull nad nim wiec to niepotrzebne
        assert questions != null;
        questions.forEach(x -> x.setSurvey(this));
    }
}