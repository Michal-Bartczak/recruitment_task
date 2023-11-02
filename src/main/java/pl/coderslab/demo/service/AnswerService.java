package pl.coderslab.demo.service;

import org.springframework.stereotype.Service;
import pl.coderslab.demo.dto.AnswerRequestDto;
import pl.coderslab.demo.dto.AnswerResponseDto;
import pl.coderslab.demo.entity.Answer;
import pl.coderslab.demo.repository.AnswerRepository;
import pl.coderslab.demo.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public AnswerService(AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    public AnswerResponseDto checkAnswers(AnswerRequestDto answerRequest) {
        Long questionId = answerRequest.getQuestionId();
        List<Long> userAnswers = answerRequest.getAnswers();

        List<Answer> allAnswersByQuestionId = answerRepository.findAnswersByQuestionIdToList(questionId);
        List<Long> correctAnswerLongList = new ArrayList<>();

        for (int i = 0; i < allAnswersByQuestionId.size(); i++) {
            if (allAnswersByQuestionId.get(i).isCorrect()) {
                correctAnswerLongList.add( i + 1L);
            }
        }
        return new AnswerResponseDto(userAnswers.equals(correctAnswerLongList));
    }

    public boolean checkExistQuestionById(AnswerRequestDto answerRequest) {
        return questionRepository.existsById(answerRequest.getQuestionId());
    }

    public boolean checkExistAnswersByQuestionId(AnswerRequestDto answerRequest) {
        return answerRepository.existsByQuestionId(answerRequest.getQuestionId());
    }

}
