package com.iciak.surfey.surveyservice.service.converter;

import com.iciak.surfey.surveyservice.entity.ResultEntity;
import com.iciak.surfey.surveyservice.model.Result;
import com.iciak.surfey.userservice.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Getter
public class ResultMapper {
    private final AnswerMapper answerMapper;
    private final UserMapper userMapper;


    public Result toModel(ResultEntity entity) {
        return Result.builder()
                .user(userMapper.toModel(entity.getUser()))
                .chosenAnswer(answerMapper.toModel(entity.getChosenAnswer()))
                .uuid(entity.getUuid())
                .build();
    }
}
