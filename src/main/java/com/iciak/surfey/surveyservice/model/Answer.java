package com.iciak.surfey.surveyservice.model;

import lombok.*;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(force = true, access = PRIVATE)
public class Answer {
    @NonNull
    UUID uuid;
    @NonNull
    String content;

}