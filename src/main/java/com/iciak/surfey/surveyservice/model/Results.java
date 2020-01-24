package com.iciak.surfey.surveyservice.model;

import lombok.*;

import java.util.List;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class Results {
    @NonNull
    List<Result> results;
}