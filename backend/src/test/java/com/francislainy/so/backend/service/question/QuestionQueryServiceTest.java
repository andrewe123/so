package com.francislainy.so.backend.service.question;

import com.francislainy.so.backend.dto.question.QuestionCreateDto;
import com.francislainy.so.backend.dto.question.QuestionQueryDto;
import com.francislainy.so.backend.entity.question.QuestionEntity;
import com.francislainy.so.backend.repository.answer.AnswerRepository;
import com.francislainy.so.backend.repository.question.QuestionRepository;
import com.francislainy.so.backend.service.impl.question.QuestionCommandServiceImpl;
import com.francislainy.so.backend.service.impl.question.QuestionQueryServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class QuestionQueryServiceTest {

    @Mock
    QuestionRepository questionRepository;

    @MockBean
    private QuestionQueryService questionQueryService;

    @InjectMocks
    private QuestionQueryServiceImpl questionQueryServiceImpl;


    @Test
    public void testQuestionItemFoundOnDbNotNull() {

        Mockito.when(questionRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(new QuestionEntity()));

        assertNotNull(questionQueryServiceImpl.getMyQuestionItem(UUID.fromString("02c903f7-7a55-470d-8449-cf7587f5a3fb"), UUID.fromString("05c903f7-7a55-470d-8449-cf7587f5a3fb")));
    }


    @Test
    public void testQuestionItemFoundOnDb() {

        Mockito.when(questionRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(new QuestionEntity(UUID.fromString("02c903f7-7a55-470d-8449-cf7587f5a3fb"))));

        QuestionQueryDto questionQueryDto = questionQueryServiceImpl.getMyQuestionItem(UUID.fromString("02c903f7-7a55-470d-8449-cf7587f5a3fb"), UUID.fromString("05c903f7-7a55-470d-8449-cf7587f5a3fb"));

        assertNotNull(questionQueryDto);

        assertEquals(questionQueryDto.getId(), UUID.fromString("02c903f7-7a55-470d-8449-cf7587f5a3fb"));
    }


    @Test
    public void testNoQuestionItemFoundOnDb() {

        Mockito.when(questionRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.empty());

        assertNull(questionQueryServiceImpl.getMyQuestionItem(UUID.fromString("02c903f7-7a55-470d-8449-cf7587f5a3fb"), UUID.fromString("05c903f7-7a55-470d-8449-cf7587f5a3fb")));
    }

}