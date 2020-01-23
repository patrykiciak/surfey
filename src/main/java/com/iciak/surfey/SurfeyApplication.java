package com.iciak.surfey;

import com.iciak.surfey.surveyservice.entity.AnswerOptionEntity;
import com.iciak.surfey.surveyservice.entity.QuestionEntity;
import com.iciak.surfey.surveyservice.entity.SurveyEntity;
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

		userRepository.save(UserEntity.builder()
				.uuid(UUID.randomUUID())
				.dateOfBirth(LocalDate.of(1998, Month.APRIL, 12))
				.login("pyku")
				.password("password")
				.sex(Sex.MAN)
				.build()
		);



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
																AnswerOptionEntity.builder().uuid(UUID.randomUUID()).answer("gsdf").build(),
																AnswerOptionEntity.builder().uuid(UUID.randomUUID()).answer("gsdsfdsdff").build(),
																AnswerOptionEntity.builder().uuid(UUID.randomUUID()).answer("gsdasasddf").build(),
																AnswerOptionEntity.builder().uuid(UUID.randomUUID()).answer("gsdfsdsdfsdff").build()
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
																AnswerOptionEntity.builder().uuid(UUID.randomUUID()).answer("gsdf").build(),
																AnswerOptionEntity.builder().uuid(UUID.randomUUID()).answer("gsdsfdsdff").build(),
																AnswerOptionEntity.builder().uuid(UUID.randomUUID()).answer("gsdasasddf").build(),
																AnswerOptionEntity.builder().uuid(UUID.randomUUID()).answer("gsdfsdsdfsdff").build()
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
																AnswerOptionEntity.builder().uuid(UUID.randomUUID()).answer("gsdf").build(),
																AnswerOptionEntity.builder().uuid(UUID.randomUUID()).answer("gsdsfdsdff").build(),
																AnswerOptionEntity.builder().uuid(UUID.randomUUID()).answer("gsdasasddf").build(),
																AnswerOptionEntity.builder().uuid(UUID.randomUUID()).answer("gsdfsdsdfsdff").build()
														)
												).build())
								).build()

				)
		));
	}

}
