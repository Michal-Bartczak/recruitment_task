package pl.coderslab.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.coderslab.demo.entity.Answer;

import java.util.List;
import java.util.Map;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query("SELECT a.id AS id, a.answer AS answer FROM Answer a WHERE a.question.id = :questionId")
    Map<Long, String> findAnswersByQuestionId(@Param("questionId") Long questionId);

    @Query("SELECT a.id AS id, a.answer AS answer FROM Answer a WHERE a.question.id = :questionId")
    List<Answer> findAnswersByQuestionIdToList(@Param("questionId") Long questionId);

    @Query("SELECT COUNT(a) > 0 FROM Answer a WHERE a.question.id = :questionId")
    boolean existsByQuestionId(@Param("questionId") Long questionId);
}
