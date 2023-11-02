package pl.coderslab.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import pl.coderslab.demo.dto.QuestionToFormDto;
import pl.coderslab.demo.entity.Question;
import pl.coderslab.demo.repository.AnswerRepository;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;



@SpringBootTest
public class QuestionToFormServiceTest {
    @Mock
    private QuestionService questionService;
    @Mock
    private AnswerRepository answerRepository;
    @InjectMocks
    private QuestionToFormService questionToFormService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetQuestionToFormDto() {
        QuestionToFormDto questionToFormDto = new QuestionToFormDto();

        Map<Long, String> mockAnswersList = new HashMap<>();
        mockAnswersList.put(1L, "Odpowiedź");

        QuestionToFormDto mockQuestion = new QuestionToFormDto();
        mockQuestion.setQuestion("Przykładowe pytanie");
        mockQuestion.setAnswers(mockAnswersList);
        mockQuestion.setId(1L);

        Question question = new Question();
        question.setId(1L);
        question.setQuestion("Przykładowe pytanie");
        question.setApiId(999);
        when(questionService.getRandomQuestion()).thenReturn(question);

        when(answerRepository.findAnswersByQuestionId(question.getId())).thenReturn(mockAnswersList);

        questionToFormDto.setAnswers(answerRepository.findAnswersByQuestionId(question.getId()));
        questionToFormDto.setId(question.getId());
        questionToFormDto.setQuestion(question.getQuestion());

        assertEquals(mockQuestion.getId(), questionToFormDto.getId());
        assertEquals(mockQuestion.getQuestion(), questionToFormDto.getQuestion());
        assertEquals(mockQuestion.getAnswers(), questionToFormDto.getAnswers());
    }

}
