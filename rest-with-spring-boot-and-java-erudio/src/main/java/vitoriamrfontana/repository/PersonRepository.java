package vitoriamrfontana.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vitoriamrfontana.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
