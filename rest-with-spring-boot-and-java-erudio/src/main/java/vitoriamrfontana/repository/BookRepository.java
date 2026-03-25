package vitoriamrfontana.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vitoriamrfontana.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BookRepository extends JpaRepository<Book, Long> {


}
