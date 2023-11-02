package pl.coderslab.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import pl.coderslab.demo.entity.Question;
import pl.coderslab.demo.repository.QuestionRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class QuestionServiceTest {
    @InjectMocks
    private QuestionService questionService;

    @Mock
    private QuestionRepository questionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        questionService = new QuestionService(questionRepository);
    }

    @Test
    public void testGetCountQuestions() {
        Mockito.when(questionRepository.count()).thenReturn(10L);
        long count = questionService.getCountQuestions();
        assertEquals(10L, count);
    }

    @Test
    public void testGenerateRandomLong() {
        long max = 100;
        long generatedValue = questionService.generateRandomLong(max);
        assertTrue(generatedValue >= 1 && generatedValue <= max);
    }



    @Test
    public void testGetRandomQuestionWhenRepositoryHasQuestions() {
        long count = 5;
        Mockito.when(questionRepository.count()).thenReturn(count);
        long randomId = 3;
        Optional<Question> mockQuestion = Optional.of(new Question());
        Mockito.when(questionRepository.findById(randomId)).thenReturn(mockQuestion);

        Question randomQuestion = questionService.getRandomQuestion();

        assertEquals(mockQuestion.get(), randomQuestion);
    }

}
