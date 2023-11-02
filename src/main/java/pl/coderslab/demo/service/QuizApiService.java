package pl.coderslab.demo.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pl.coderslab.demo.dto.QuizDto;

import java.util.List;

@Service
public class QuizApiService {
    private static final String API_URL = "https://quizapi.io/api/v1/questions?apiKey=WMVyWQmBXhIobK9KkbYSHaNuuGOZK7Porz0AFCBC&limit=10&tags=JavaScript";
    private static final String API_KEY = "WMVyWQmBXhIobK9KkbYSHaNuuGOZK7Porz0AFCBC";

    private final RestTemplate restTemplate;

    public QuizApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<QuizDto> fetchQuizData() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("apiKey", API_KEY);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<List<QuizDto>> response = restTemplate.exchange(API_URL, HttpMethod.GET, entity, new ParameterizedTypeReference<List<QuizDto>>() {
            });
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                throw new RuntimeException("Failed to fetch quiz data from the API. Status code: " + response.getStatusCodeValue());
            }
        } catch (HttpServerErrorException e) {
            throw new RuntimeException("API returned a server error: " + e.getStatusCode() + " " + e.getStatusText(), e);
        } catch (RestClientException e) {
            throw new RuntimeException("An error occurred while calling the API: " + e.getMessage(), e);
        }
    }

}
