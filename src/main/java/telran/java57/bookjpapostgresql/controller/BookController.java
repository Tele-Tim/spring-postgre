package telran.java57.bookjpapostgresql.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import telran.java57.bookjpapostgresql.dto.AuthorDto;
import telran.java57.bookjpapostgresql.dto.BookDto;
import telran.java57.bookjpapostgresql.service.BookService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BookController {
    final BookService bookService;

    @PostMapping("/book")
    public Boolean addBook(@RequestBody BookDto bookDto) {
        return bookService.addBook(bookDto);
    }

    @GetMapping("/book/{isbn}")
    public BookDto findBookByIsbn(@PathVariable String isbn) {
        return bookService.findBookByIsbn(isbn);
    }

    @DeleteMapping("/book/{isbn}")
    public BookDto removeBook(@PathVariable String isbn) {
        return bookService.removeBook(isbn);
    }

    @PutMapping("/book/{isbn}/title/{newTitle}")
    public BookDto updateBookTitle(@PathVariable String isbn, @PathVariable String newTitle) {
        return bookService.updateBookTitle(isbn, newTitle);
    }

    @GetMapping("/books/author/{authorName}")
    public List<BookDto> findBooksByAuthor(@PathVariable String authorName) {
        return bookService.findBooksByAuthorJPQL(authorName);
    }

    @GetMapping("/books/publisher/{publisherName}")
    public List<BookDto> findBooksByPublisher(@PathVariable String publisherName) {
        return bookService.findBooksByPublisherJPQL(publisherName);
    }

    @GetMapping("/authors/book/{isbn}")
    public List<AuthorDto> findAuthorsByBookIsbn(@PathVariable String isbn) {
        return bookService.findAuthorsByBookIsbnJPQL(isbn);
    }

    @GetMapping("/publishers/author/{authorName}")
    public List<String> findPublishersByAuthor(@PathVariable String authorName) {
        return bookService.findPublishersByAuthorJPQL(authorName);
    }

    @DeleteMapping("/author/{authorName}")
    public AuthorDto removeAuthor(@PathVariable String authorName) {
        return bookService.removeAuthor(authorName);
    }

}
