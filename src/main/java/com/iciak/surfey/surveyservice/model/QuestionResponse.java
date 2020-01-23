package com.iciak.surfey.surveyservice.model;

import com.iciak.surfey.userservice.model.User;
import lombok.*;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(force = true, access = PRIVATE)
public class QuestionResponse {
    @NonNull
    UUID uuid;
    @NonNull
    AnswerOption chosenAnswer;
    @NonNull
    User user;
}
