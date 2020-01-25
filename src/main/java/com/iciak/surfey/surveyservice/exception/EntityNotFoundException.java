package com.iciak.surfey.surveyservice.exception;

import lombok.NonNull;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(@NonNull final String message) {
        super(message);
    }
}