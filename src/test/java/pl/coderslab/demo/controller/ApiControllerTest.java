package pl.coderslab.demo.controller;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import pl.coderslab.demo.dto.AnswerRequestDto;
import pl.coderslab.demo.dto.AnswerResponseDto;
import pl.coderslab.demo.dto.QuestionToFormDto;
import pl.coderslab.demo.service.AnswerService;
import pl.coderslab.demo.service.QuestionService;
import pl.coderslab.demo.service.QuestionToFormService;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
public class ApiControllerTest {
    @Mock
    private  QuestionService questionService;
    @Mock
    private  QuestionToFormService questionToFormService;
    @Mock
    private  AnswerService answerService;

    private ApiController apiController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        apiController = new ApiController(questionService,questionToFormService, answerService);
    }
    @Test
    public void testGetQuestion(){
        QuestionToFormDto questionToFormDto = new QuestionToFormDto();

        when(questionToFormService.getQuestionToFormDto()).thenReturn(questionToFormDto);

        QuestionToFormDto result = apiController.getQuestion();

        assertEquals(questionToFormDto, result);
    }
    @Test
    public void testCheckAnswerWithValidData(){
        AnswerRequestDto answerRequestDto = new AnswerRequestDto();
        answerRequestDto.setQuestionId(1L);
        answerRequestDto.setAnswers(Arrays.asList(1L,2L));

        when(answerService.checkExistAnswersByQuestionId(answerRequestDto)).thenReturn(true);
        when(answerService.checkExistQuestionById(answerRequestDto)).thenReturn(true);
        AnswerResponseDto mockResponseDto = new AnswerResponseDto(true);

        when(answerService.checkAnswers(answerRequestDto)).thenReturn(mockResponseDto);

        ResponseEntity<AnswerResponseDto> result = apiController.checkAnswers(answerRequestDto);

        assertEquals(ResponseEntity.ok(mockResponseDto), result);
    }
    @Test
    public void testCheckAnswerWithInvalidDate(){

        AnswerRequestDto answerRequest = new AnswerRequestDto();
        answerRequest.setQuestionId(1L);

        when(answerService.checkExistAnswersByQuestionId(answerRequest)).thenReturn(true);
        when(answerService.checkExistQuestionById(answerRequest)).thenReturn(false);
        AnswerResponseDto mockResponseDto = new AnswerResponseDto(false);

        ResponseEntity<AnswerResponseDto> result = apiController.checkAnswers(answerRequest);

        assertEquals(ResponseEntity.notFound().build(), result);
    }
}
