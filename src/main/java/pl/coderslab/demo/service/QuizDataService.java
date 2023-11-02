package pl.coderslab.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.demo.dto.QuizDto;
import pl.coderslab.demo.entity.Answer;
import pl.coderslab.demo.entity.Question;
import pl.coderslab.demo.repository.AnswerRepository;
import pl.coderslab.demo.repository.QuestionRepository;

import java.util.List;
import java.util.Map;

@Service
public class QuizDataService {
    private final QuizApiService quizApiService;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public QuizDataService(QuizApiService quizApiService, QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.quizApiService = quizApiService;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @Transactional
    public void processAndSaveQuizData() {
        List<QuizDto> quizData = quizApiService.fetchQuizData();

        for (QuizDto quizDto : quizData) {
            Question question = new Question();
            question.setQuestion(quizDto.getQuestion());
            question.setApiId(quizDto.getId());
            questionRepository.save(question);

            for (Map.Entry<String, String> entry : quizDto.getAnswers().entrySet()) {
                Answer answer = new Answer();
                if (entry.getValue() != null) {
                    answer.setAnswer(entry.getValue());
                    if (entry.getKey().equals(quizDto.getCorrect_answer())) {
                        answer.setCorrect(true);
                    } else {
                        answer.setCorrect(false);
                    }

                    answer.setQuestion(question);
                    answerRepository.save(answer);
                }
            }
        }
    }
}
