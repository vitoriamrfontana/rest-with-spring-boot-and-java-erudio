package vitoriamrfontana.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vitoriamrfontana.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
