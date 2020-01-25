package com.iciak.surfey;

import com.iciak.surfey.surveyservice.entity.AnswerEntity;
import com.iciak.surfey.surveyservice.entity.QuestionEntity;
import com.iciak.surfey.surveyservice.entity.ResultEntity;
import com.iciak.surfey.surveyservice.entity.SurveyEntity;
import com.iciak.surfey.surveyservice.repository.ResultRepository;
import com.iciak.surfey.surveyservice.repository.SurveyRepository;
import com.iciak.surfey.userservice.entity.UserEntity;
import com.iciak.surfey.userservice.enumerated.Sex;
import com.iciak.surfey.userservice.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

@SpringBootApplication
public class SurfeyApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SurfeyApplication.class, args);

        SurveyRepository surveyRepository = ctx.getBean(SurveyRepository.class);
        UserRepository userRepository = ctx.getBean(UserRepository.class);


        UserEntity user = UserEntity.builder()
                .uuid(UUID.randomUUID())
                .dateOfBirth(LocalDate.of(1998, Month.APRIL, 12))
                .login("pyku")
                .password("password")
                .sex(Sex.MAN)
                .build();
        userRepository.save(user);

        AnswerEntity answerEntity = AnswerEntity.builder().uuid(UUID.randomUUID()).content("gsdf").build();

        surveyRepository.saveAll(new ArrayList<>(
                Arrays.asList(
                        SurveyEntity.builder()
                                .name("Ankietka 1")
                                .uuid(UUID.randomUUID())
                                .questions(Collections.singletonList(
                                        QuestionEntity.builder()
                                                .uuid(UUID.randomUUID())
                                                .content("Co robisz?")
                                                .answers(
                                                        Arrays.asList(
                                                                answerEntity,
                                                                AnswerEntity.builder().uuid(UUID.randomUUID()).content("gsdsfdsdff").build(),
                                                                AnswerEntity.builder().uuid(UUID.randomUUID()).content("gsdasasddf").build(),
                                                                AnswerEntity.builder().uuid(UUID.randomUUID()).content("gsdfsdsdfsdff").build()
                                                        )
                                                ).build())
                                ).build(),


                        SurveyEntity.builder()
                                .name("Ankietka 1")
                                .uuid(UUID.randomUUID())
                                .questions(Collections.singletonList(
                                        QuestionEntity.builder()
                                                .uuid(UUID.randomUUID())
                                                .content("Co robisz?")
                                                .answers(
                                                        Arrays.asList(
                                                                AnswerEntity.builder().uuid(UUID.randomUUID()).content("gsdf").build(),
                                                                AnswerEntity.builder().uuid(UUID.randomUUID()).content("gsdsfdsdff").build(),
                                                                AnswerEntity.builder().uuid(UUID.randomUUID()).content("gsdasasddf").build(),
                                                                AnswerEntity.builder().uuid(UUID.randomUUID()).content("gsdfsdsdfsdff").build()
                                                        )
                                                ).build())
                                ).build(),
                        SurveyEntity.builder()
                                .name("Ankietka 1")
                                .uuid(UUID.randomUUID())
                                .questions(Collections.singletonList(
                                        QuestionEntity.builder()
                                                .uuid(UUID.randomUUID())
                                                .content("Co robisz?")
                                                .answers(
                                                        Arrays.asList(
                                                                AnswerEntity.builder().uuid(UUID.randomUUID()).content("gsdf").build(),
                                                                AnswerEntity.builder().uuid(UUID.randomUUID()).content("gsdsfdsdff").build(),
                                                                AnswerEntity.builder().uuid(UUID.randomUUID()).content("gsdasasddf").build(),
                                                                AnswerEntity.builder().uuid(UUID.randomUUID()).content("gsdfsdsdfsdff").build()
                                                        )
                                                ).build())
                                ).build()

                )
        ));

        ResultRepository resultRepository = ctx.getBean(ResultRepository.class);
        resultRepository.save(ResultEntity.builder()
                .uuid(UUID.randomUUID())
                .chosenAnswer(answerEntity)
                .userUuid(user.getUuid())
                .build());
    }

}
