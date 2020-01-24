package com.iciak.surfey.surveyservice.model;

import com.iciak.surfey.userservice.model.User;
import lombok.*;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(force = true, access = PRIVATE)
public class Result {
    @NonNull
    UUID uuid;
    @NonNull
    Answer chosenAnswer;
    @NonNull
    User user;
}
