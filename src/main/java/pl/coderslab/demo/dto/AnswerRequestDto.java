package pl.coderslab.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AnswerRequestDto {
    private List<Long> answers;
    private Long questionId;
}
