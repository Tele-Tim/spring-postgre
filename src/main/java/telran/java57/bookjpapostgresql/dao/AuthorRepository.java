package telran.java57.bookjpapostgresql.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import telran.java57.bookjpapostgresql.model.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, String> {

    @Query("SELECT a FROM Book b JOIN b.authors a WHERE LOWER(b.isbn) = LOWER(:isbn)")
    List<Author> findAuthorsByBookIsbn(@Param("isbn") String isbn);
}
