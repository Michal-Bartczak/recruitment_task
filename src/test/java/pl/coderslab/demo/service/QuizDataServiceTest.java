package pl.coderslab.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import pl.coderslab.demo.dto.QuizDto;
import pl.coderslab.demo.entity.Answer;
import pl.coderslab.demo.entity.Question;
import pl.coderslab.demo.repository.AnswerRepository;
import pl.coderslab.demo.repository.QuestionRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class QuizDataServiceTest {
    @Mock
    private QuizApiService quizApiService;
    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private AnswerRepository answerRepository;
    @InjectMocks
    private QuizDataService quizDataService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessAndSaveQuizData() {
        // Przygotowanie danych do mockowania
        QuizDto quizDto = new QuizDto();
        quizDto.setQuestion("Test question");
        quizDto.setId(1L);
        Map<String, String> answers = new HashMap<>();
        answers.put("A", "Answer A");
        answers.put("B", "Answer B");
        answers.put("C", "Answer C");
        answers.put("D", "Answer D");
        quizDto.setAnswers(answers);
        quizDto.setCorrect_answer("B");
        quizDto.setMultiple_correct_answers(false);
        Map<String, Boolean> testCorrectAnswers = new HashMap<>();
        testCorrectAnswers.put("A", false);
        testCorrectAnswers.put("B", true);
        testCorrectAnswers.put("C", false);
        testCorrectAnswers.put("D", false);
        quizDto.setCorrect_answers(testCorrectAnswers);

        List<QuizDto> quizData = List.of(quizDto);

        // Mockowanie odpowiedzi z QuizApiService
        when(quizApiService.fetchQuizData()).thenReturn(quizData);

        // Wywołanie metody, którą testujemy
        QuizDataService quizDataService = new QuizDataService(quizApiService, questionRepository, answerRepository);
        quizDataService.processAndSaveQuizData();

        // Sprawdzenie, czy metoda questionRepository.save została wywołana raz
        verify(questionRepository, Mockito.times(1)).save(Mockito.any(Question.class));

        // Sprawdzenie, czy metoda answerRepository.save została wywołana trzy razy (dla trzech odpowiedzi A, B, C, D)
        verify(answerRepository, Mockito.times(4)).save(Mockito.any(Answer.class));


    }

}
