package com.iciak.surfey.userservice.entity;

import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.iciak.surfey.userservice.enumerated.Sex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(force = true, access = PRIVATE)
@Data
@Builder
@Entity(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue
    private final int id;
    @NonNull
    @Column(unique = true)
    // FINAL
    private UUID uuid;
    @NonNull
    @Column(unique = true)
    private String login;
    @NonNull
    private String password;
    @NonNull

    // REVIEW: @Enumerated(EnumType.STRING)
    private Sex sex;
    @NonNull
    private LocalDate dateOfBirth;
}