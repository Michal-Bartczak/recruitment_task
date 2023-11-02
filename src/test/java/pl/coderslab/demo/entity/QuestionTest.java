package pl.coderslab.demo.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class QuestionTest {
    private Question question;

    @BeforeEach
    void setUp() {
        question = new Question();
    }

    @Test
    void getId() {
        long id = 1L;
        question.setId(id);
        assertEquals(id, question.getId());
    }

    @Test
    void getApiId() {
        long apiId = 42L;
        question.setApiId(apiId);
        assertEquals(apiId, question.getApiId());
    }

    @Test
    void getQuestion() {
        String questionText = "Sample question";
        question.setQuestion(questionText);
        assertEquals(questionText, question.getQuestion());
    }
}
