package pl.coderslab.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import pl.coderslab.demo.dto.AnswerRequestDto;
import pl.coderslab.demo.dto.AnswerResponseDto;
import pl.coderslab.demo.entity.Answer;
import pl.coderslab.demo.entity.Question;
import pl.coderslab.demo.repository.AnswerRepository;
import pl.coderslab.demo.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AnswerServiceTest {
    @Mock
    private AnswerRepository answerRepository;
    @Mock
    private QuestionRepository questionRepository;
    @InjectMocks
    private AnswerService answerService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testCheckAnswersWithCorrectAnswers() {
        AnswerRequestDto answerRequest = new AnswerRequestDto();
        answerRequest.setQuestionId(1L);
        answerRequest.setAnswers(Arrays.asList(1L, 2L, 3L));

        List<Answer> mockAnswers = Arrays.asList(
                new Answer(1L, "Odpowiedź 1", true,null),
                new Answer(2L, "Odpowiedź 2", true,null),
                new Answer(3L, "Odpowiedź 3", true,null));

        when(answerRepository.findAnswersByQuestionIdToList(answerRequest.getQuestionId())).thenReturn(mockAnswers);

        List<Long> correctList = new ArrayList<>();
        for (int i = 0; i < mockAnswers.size(); i ++){
            if (mockAnswers.get(i).isCorrect()){
                correctList.add(i + 1L);
            }
        }
        assertEquals(correctList, answerRequest.getAnswers());
    }
    @Test
    public void testCheckAnswersWithIncorrectAnswers(){
        AnswerRequestDto answerRequest = new AnswerRequestDto();
        answerRequest.setQuestionId(1L);
        answerRequest.setAnswers(Arrays.asList(1L, 2L, 3L));
        List<Answer> mockAnswers = Arrays.asList(
                new Answer(1L, "Odpowiedź 1", true,null),
                new Answer(2L, "Odpowiedź 2", true,null),
                new Answer(3L, "Odpowiedź 3", false,null));

        when(answerRepository.findAnswersByQuestionIdToList(answerRequest.getQuestionId())).thenReturn(mockAnswers);

        List<Long> correctList = new ArrayList<>();
        for (int i = 0; i < mockAnswers.size(); i ++){
            if (mockAnswers.get(i).isCorrect()){
                correctList.add(i + 1L);
            }
        }
        assertNotEquals(correctList, answerRequest.getAnswers());
    }

}
