package com.iciak.surfey.surveyservice.service.mapper;

import com.iciak.surfey.surveyservice.entity.AnswerEntity;
import com.iciak.surfey.surveyservice.entity.ResultEntity;
import com.iciak.surfey.surveyservice.model.Result;
import com.iciak.surfey.userservice.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
@Getter
public class ResultMapper {
    private final AnswerMapper answerMapper;
    private final UserMapper userMapper;


    public Result toModel(@NonNull final ResultEntity entity) {
        return Result.builder()
                .userUuid(entity.getUserUuid())
                .answerUuid(entity.getChosenAnswer().getUuid())
                .uuid(entity.getUuid())
                .build();
    }

    public ResultEntity createResult(@NonNull final Result result, @NonNull final AnswerEntity chosenAnswer) {
        return ResultEntity.builder()
                .chosenAnswer(chosenAnswer)
                .uuid(UUID.randomUUID())
                .userUuid(result.getUserUuid())
                .build();
    }
}
