package com.iciak.surfey.userservice.model;

import com.iciak.surfey.userservice.enumerated.Sex;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(force = true, access = PRIVATE)
@Value
@Builder
public class User {
    @NonNull
    UUID uuid;
    @NonNull
    String login;
    @NonNull
    String password;
    @NonNull
    Sex sex;
    @NonNull
    LocalDate dateOfBirth;
}