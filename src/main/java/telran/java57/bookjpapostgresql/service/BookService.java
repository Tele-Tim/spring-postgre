package telran.java57.bookjpapostgresql.service;

import org.springframework.stereotype.Service;
import telran.java57.bookjpapostgresql.dto.AuthorDto;
import telran.java57.bookjpapostgresql.dto.BookDto;

import java.util.List;

@Service
public interface BookService {
    Boolean addBook(BookDto bookDto);

    BookDto findBookByIsbn(String isbn);

    BookDto removeBook(String isbn);

    BookDto updateBookTitle(String isbn, String newTitle);

    List<BookDto> findBooksByAuthor(String authorName);

    List<BookDto> findBooksByPublisher(String publisherName);

    List<AuthorDto> findAuthorsByBookIsbn(String isbn);

    List<String> findPublishersByAuthor(String authorName);

    AuthorDto removeAuthor(String authorName);

        //JPQL Methods
    List<BookDto> findBooksByAuthorJPQL(String authorName);

    List<BookDto> findBooksByPublisherJPQL(String publisherName);

    List<AuthorDto> findAuthorsByBookIsbnJPQL(String isbn);

    List<String> findPublishersByAuthorJPQL(String authorName);
}
