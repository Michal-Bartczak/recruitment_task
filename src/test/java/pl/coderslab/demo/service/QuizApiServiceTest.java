package pl.coderslab.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pl.coderslab.demo.dto.QuizDto;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class QuizApiServiceTest {

    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private QuizApiService quizApiService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void testFetchQuizData_Success() {
        // Mockowanie odpowiedzi z API
        List<QuizDto> expectedQuizData = Arrays.asList(new QuizDto(), new QuizDto());
        ResponseEntity<List<QuizDto>> mockResponse = new ResponseEntity<>(expectedQuizData, HttpStatus.OK);
        Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.GET), Mockito.any(), Mockito.<Class<List<QuizDto>>>any()))
                .thenReturn(mockResponse);

        List<QuizDto> result = quizApiService.fetchQuizData();

        assertEquals(expectedQuizData, result);
    }


    @Test
    void testFetchQuizData_ApiError() {
        // Mockowanie błędu z API
        ResponseEntity<List<QuizDto>> mockResponse = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.GET), Mockito.any(), Mockito.<Class<List<QuizDto>>>any()))
                .thenReturn(mockResponse);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> quizApiService.fetchQuizData());
        assertEquals("Failed to fetch quiz data from the API. Status code: 500", exception.getMessage());
    }

    @Test
    void testFetchQuizData_RestClientError() {
        // Mockowanie RestClientException
        Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.GET), Mockito.any(), Mockito.<Class<List<QuizDto>>>any()))
                .thenThrow(new RestClientException("Rest client error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> quizApiService.fetchQuizData());
        assertEquals("An error occurred while calling the API: Rest client error", exception.getMessage());
    }

}
