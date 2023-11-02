package pl.coderslab.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AnswerResponseDto {
    private boolean correct;

    public AnswerResponseDto(boolean correct) {
        this.correct = correct;
    }
}
