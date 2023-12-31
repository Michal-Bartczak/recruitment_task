package pl.coderslab.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.demo.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
