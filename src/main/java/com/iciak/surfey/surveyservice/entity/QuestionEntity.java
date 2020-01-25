package com.iciak.surfey.surveyservice.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.*;
import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@NoArgsConstructor(access = PRIVATE, force = true)
@AllArgsConstructor(access = PRIVATE)
@Entity(name = "questions")
public class QuestionEntity {
    @Id
    @GeneratedValue
    private final int id;
    @Column(unique = true)
    @NonNull
    private final UUID uuid;
    @NonNull
    @OneToMany(cascade = {DETACH, PERSIST, REFRESH, REMOVE})
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "question_id")
    private final List<AnswerEntity> answers;
    @NonNull
    private final String content;
}