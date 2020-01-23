package com.iciak.surfey.userservice.entity;

import com.iciak.surfey.userservice.enumerated.Sex;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.time.LocalDate;
import java.util.UUID;

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
    private UUID uuid;
    @NonNull
    private String login;
    @NonNull
    private String password;
    @NonNull
    private Sex sex;
    @NonNull
    private LocalDate dateOfBirth;
}