package com.iciak.surfey.surveyservice.entity;

import com.iciak.surfey.surveyservice.model.AnswerOption;
import com.iciak.surfey.userservice.entity.UserEntity;
import com.iciak.surfey.userservice.model.User;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@NoArgsConstructor(access = PRIVATE, force = true)
@AllArgsConstructor(access = PRIVATE)
@Entity(name = "question_responses")
public class QuestionResponseEntity {
    @Id
    @GeneratedValue
    private final int id;
    @NonNull
    @Column(unique = true)
    private final UUID uuid;
    @NonNull
    @OneToOne
    private final AnswerOptionEntity chosenAnswer;
    @NonNull
    @OneToOne
    private final UserEntity user;
}