package pl.coderslab.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.demo.dto.AnswerResponseDto;
import pl.coderslab.demo.dto.QuestionToFormDto;
import pl.coderslab.demo.service.AnswerService;
import pl.coderslab.demo.service.QuestionService;
import pl.coderslab.demo.service.QuestionToFormService;
import pl.coderslab.demo.dto.AnswerRequestDto;

@RestController
public class ApiController {
    private final QuestionService questionService;
    private final QuestionToFormService questionToFormService;
    private final AnswerService answerService;

    public ApiController(QuestionService questionService, QuestionToFormService questionToFormService, AnswerService answerService) {
        this.questionService = questionService;
        this.questionToFormService = questionToFormService;
        this.answerService = answerService;
    }

    @GetMapping(value = "/api/questions", produces = "application/json")
    public QuestionToFormDto getQuestion() {
        return questionToFormService.getQuestionToFormDto();
    }

    @PostMapping("/api/answers")
    public ResponseEntity<AnswerResponseDto> checkAnswers(@RequestBody AnswerRequestDto answerRequest) {
        if (answerService.checkExistAnswersByQuestionId(answerRequest) && answerService.checkExistQuestionById(answerRequest)) {
            return ResponseEntity.ok(answerService.checkAnswers(answerRequest));
        }
        return ResponseEntity.notFound().build();
    }
}
