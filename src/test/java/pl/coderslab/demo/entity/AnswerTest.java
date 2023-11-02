package pl.coderslab.demo.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AnswerTest {
    private Answer answer;

    @BeforeEach
    void setUp() {
        answer = new Answer();
    }

    @Test
    void getId() {
        long id = 1L;
        answer.setId(id);
        assertEquals(id, answer.getId());
    }

    @Test
    void getAnswer() {
        String answerText = "Sample answer";
        answer.setAnswer(answerText);
        assertEquals(answerText, answer.getAnswer());
    }

    @Test
    void isCorrect() {
        boolean correct = true;
        answer.setCorrect(correct);
        assertEquals(correct, answer.isCorrect());
    }

    @Test
    void getQuestion() {
        Question question = new Question();
        answer.setQuestion(question);
        assertEquals(question, answer.getQuestion());
    }
}
