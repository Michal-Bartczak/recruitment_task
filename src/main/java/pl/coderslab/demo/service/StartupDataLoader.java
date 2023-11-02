package pl.coderslab.demo.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class StartupDataLoader implements CommandLineRunner {
    private final QuizDataService quizDataService;

    public StartupDataLoader(QuizDataService quizDataService) {
        this.quizDataService = quizDataService;
    }

    @Override
    public void run(String... args) throws Exception {
        quizDataService.processAndSaveQuizData();
    }
}
