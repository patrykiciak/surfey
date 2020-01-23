package com.iciak.surfey.surveyservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@NoArgsConstructor(access = PRIVATE, force = true)
@AllArgsConstructor(access = PRIVATE)
@Entity(name = "answer_options")
public class AnswerOptionEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private final int id;
    @Column(unique = true)
    private final UUID uuid;
    private final String answer;
}