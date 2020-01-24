package com.iciak.surfey.surveyservice.entity;

import com.iciak.surfey.userservice.entity.UserEntity;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

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
    @Column(unique = true)
    @NonNull
    private final UUID uuid;
    @NonNull
    @OneToOne
    private final UserEntity user;
    @NonNull
    @ManyToOne
    @JoinColumn
    private final AnswerEntity chosenAnswer;
}