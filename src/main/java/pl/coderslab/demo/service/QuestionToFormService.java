package pl.coderslab.demo.service;

import org.springframework.stereotype.Service;
import pl.coderslab.demo.dto.QuestionToFormDto;
import pl.coderslab.demo.entity.Question;
import pl.coderslab.demo.repository.AnswerRepository;

@Service
public class QuestionToFormService {
    private final QuestionService questionService;
    private final AnswerRepository answerRepository;

    public QuestionToFormService(QuestionService questionService, AnswerRepository answerRepository) {
        this.questionService = questionService;
        this.answerRepository = answerRepository;
    }

    public QuestionToFormDto getQuestionToFormDto() {
        QuestionToFormDto questionToFormDto = new QuestionToFormDto();

        Question question = questionService.getRandomQuestion();
        questionToFormDto.setId(question.getId());
        questionToFormDto.setQuestion(question.getQuestion());
        questionToFormDto.setAnswers(answerRepository.findAnswersByQuestionId(question.getId()));

        return questionToFormDto;

    }

}
