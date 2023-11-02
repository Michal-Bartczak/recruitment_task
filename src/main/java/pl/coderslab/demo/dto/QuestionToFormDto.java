package pl.coderslab.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Objects;

@Getter
@Setter
public class QuestionToFormDto {
    private long id;
    private String question;
    private Map<Long, String> answers;

}
