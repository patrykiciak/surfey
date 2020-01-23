package com.iciak.surfey.surveyservice.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(force = true, access = PRIVATE)
public class Survey {
    @NonNull
    UUID uuid;
    @NonNull
    List<Question> questions;
    @NonNull
    String name;
}