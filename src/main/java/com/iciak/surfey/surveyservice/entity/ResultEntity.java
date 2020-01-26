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
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"uuid"})
})
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
    @JoinColumn
    private AnswerEntity chosenAnswer;
}