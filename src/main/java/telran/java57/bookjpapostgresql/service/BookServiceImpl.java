package telran.java57.bookjpapostgresql.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import telran.java57.bookjpapostgresql.dao.AuthorRepository;
import telran.java57.bookjpapostgresql.dao.BookRepository;
import telran.java57.bookjpapostgresql.dao.PublisherRepository;
import telran.java57.bookjpapostgresql.dto.AuthorDto;
import telran.java57.bookjpapostgresql.dto.BookDto;
import telran.java57.bookjpapostgresql.exceptions.AuthorNotFoundException;
import telran.java57.bookjpapostgresql.exceptions.BookNotFoundException;
import telran.java57.bookjpapostgresql.model.Author;
import telran.java57.bookjpapostgresql.model.Book;
import telran.java57.bookjpapostgresql.model.Publisher;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService, CommandLineRunner {
    final BookRepository bookRepository;
    final AuthorRepository authorRepository;
    final PublisherRepository publisherRepository;
    final ModelMapper modelMapper;


    @Override
    public Boolean addBook(BookDto bookDto) {
        if (bookRepository.existsById(bookDto.getIsbn())) {
            return false;
        }
        Publisher publisher = publisherRepository.findById(bookDto.getPublisher().getPublisherName())
                .orElse(publisherRepository.save(new Publisher(bookDto.getPublisher().getPublisherName())));

        Set<Author> authors = bookDto.getAuthors().stream()
                .map(a -> authorRepository.findById(a.getName())
                        .orElse(authorRepository.save(modelMapper.map(a, Author.class))))
                .collect(Collectors.toSet());

        Book book = new Book(bookDto.getIsbn(), bookDto.getTitle(), publisher, authors);
        bookRepository.save(book);
        return true;
    }

    @Override
    public BookDto findBookByIsbn(String isbn) {
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
        return modelMapper.map(book, BookDto.class);
    }

    @Override
    @Transactional
    public BookDto removeBook(String isbn) {
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
        bookRepository.delete(book);
        return modelMapper.map(book, BookDto.class);
    }

    @Override
    @Transactional
    public BookDto updateBookTitle(String isbn, String newTitle) {
        Book book = bookRepository.findById(isbn).orElseThrow(BookNotFoundException::new);
        book.setTitle(newTitle);
        bookRepository.save(book);
        return modelMapper.map(book, BookDto.class);
    }

    //Speed 26ms
    @Override
    public List<BookDto> findBooksByAuthor(String authorName) {
        return bookRepository.findAll().stream()
                .map(b -> modelMapper.map(b, BookDto.class))
                .filter(bDto -> bDto.getAuthors().stream()
                        .anyMatch(a -> a.getName().equalsIgnoreCase(authorName)))
                .toList();
    }

    //Speed 12ms
    @Override
    public List<BookDto> findBooksByAuthorJPQL(String authorName) {
        return bookRepository.findBooksByAuthorJPQL(authorName).stream()
                .map(b -> modelMapper.map(b, BookDto.class))
                .toList();
    }


    @Override
    public List<BookDto> findBooksByPublisher(String publisherName) {
        return bookRepository.findAll().stream()
                .map(b -> modelMapper.map(b, BookDto.class))
                .filter(b -> b.getPublisher().getPublisherName().equalsIgnoreCase(publisherName))
                .toList();
    }

    @Override
    public List<BookDto> findBooksByPublisherJPQL(String publisherName) {
        return bookRepository.findBooksByPublisherJPQL(publisherName).stream()
                .map(b -> modelMapper.map(b, BookDto.class))
                .toList();
    }

    @Override
    public List<AuthorDto> findAuthorsByBookIsbn(String isbn) {
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
        return book.getAuthors().stream()
                .map(a -> modelMapper.map(a, AuthorDto.class))
                .toList();
    }

    @Override
    public List<AuthorDto> findAuthorsByBookIsbnJPQL(String isbn) {
        return authorRepository.findAuthorsByBookIsbn(isbn).stream()
                .map(a -> modelMapper.map(a, AuthorDto.class))
                .toList();
    }

    @Override
    public List<String> findPublishersByAuthor(String authorName) {
        Author author = authorRepository.findById(authorName).orElseThrow(AuthorNotFoundException::new);
        return bookRepository.findAll().stream()
                .filter(b -> b.getAuthors().contains(author))
                .map(b -> b.getPublisher().getPublisherName())
                .toList();
    }

    @Override
    public List<String> findPublishersByAuthorJPQL(String authorName) {
        return bookRepository.findBooksByAuthorJPQL(authorName).stream()
                .map(b -> b.getPublisher().getPublisherName())
                .toList();
    }

    @Override
    @Transactional
    public AuthorDto removeAuthor(String authorName) {
        Author author = authorRepository.findById(authorName).orElseThrow(AuthorNotFoundException::new);
        bookRepository.findBooksByAuthorJPQL(authorName).stream()
                .forEach(b -> bookRepository.delete(b));
        authorRepository.delete(author);
        return modelMapper.map(author, AuthorDto.class);
    }


    @Override
    public void run(String... args) throws Exception {
        if (bookRepository.count() == 0) {
            List<Book> books = DataGenerator.generateBooks();
            Set<Author> authors = books.stream()
                    .flatMap(b -> b.getAuthors().stream())
                    .collect(Collectors.toSet());
            Set<Publisher> publishers = books.stream()
                    .map(Book::getPublisher)
                    .collect(Collectors.toSet());

            publisherRepository.saveAll(publishers);
            authorRepository.saveAll(authors);
            bookRepository.saveAll(books);
        }
    }
}
