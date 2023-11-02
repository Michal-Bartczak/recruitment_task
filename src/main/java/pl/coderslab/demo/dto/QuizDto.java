package pl.coderslab.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class QuizDto {
    private Long id;
    private String question;
    private Map<String, String> answers;
    private Boolean multiple_correct_answers;
    private Map<String, Boolean> correct_answers;
    private String correct_answer;

}