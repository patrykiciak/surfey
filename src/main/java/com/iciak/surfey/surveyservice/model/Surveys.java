package com.iciak.surfey.surveyservice.model;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)//REVIEW: static import
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)//REVIEW: static import
public class Surveys {
    @NonNull
    List<Survey> surveys;
}