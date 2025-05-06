package telran.java57.bookjpapostgresql.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import telran.java57.bookjpapostgresql.model.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {

    @Query("SELECT b FROM Book b JOIN b.authors a WHERE LOWER(a.name) = LOWER(:authorName)")
    List<Book> findBooksByAuthorJPQL(@Param("authorName") String authorName);

    @Query("SELECT b FROM Book b JOIN b.publisher p WHERE LOWER(p.publisherName) = LOWER(:publisherName)")
    List<Book> findBooksByPublisherJPQL(@Param("publisherName")String publisherName);
}
