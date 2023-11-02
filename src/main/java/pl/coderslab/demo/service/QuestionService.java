package pl.coderslab.demo.service;

import org.springframework.stereotype.Service;
import pl.coderslab.demo.entity.Question;
import pl.coderslab.demo.repository.QuestionRepository;

import java.util.Optional;
import java.util.Random;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public long getCountQuestions() {
        return questionRepository.count();
    }

    public long generateRandomLong(long max) {
        Random random = new Random();
        return random.nextLong(max) + 1;
    }

    public Question getRandomQuestion() {
        long count = getCountQuestions();
        long randomId = generateRandomLong(count);

        Optional<Question> randomQuestion = questionRepository.findById(randomId);

        return randomQuestion.orElse(new Question());
    }
}
