package telran.java57.bookjpapostgresql.service;

import telran.java57.bookjpapostgresql.model.Author;
import telran.java57.bookjpapostgresql.model.Book;
import telran.java57.bookjpapostgresql.model.Publisher;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class DataGenerator {
    public static List<Book> generateBooks() {
        Publisher p1 = new Publisher("Publisher1");
        Publisher p2 = new Publisher("Publisher2");
        Publisher p3 = new Publisher("Publisher3");

        Author a1 = new Author("Author1", LocalDate.of(2000, 1, 5));
        Author a2 = new Author("Author2", LocalDate.of(1990, 5, 10));
        Author a3 = new Author("Author3", LocalDate.of(1980, 10, 15));


        return List.of(
                new Book(
                        "1000",
                        "Title1",
                        p1,
                        Set.of(a1)
                ),
                new Book(
                        "2000",
                        "Title2",
                        p1,
                        Set.of(a2)
                ),
                new Book(
                        "3000",
                        "Title3",
                        p3,
                        Set.of(a3)
                ),
                new Book(
                        "4000",
                        "Title4",
                        p2,
                        Set.of(a3, a1)
                ),
                new Book(
                        "5000",
                        "Title5",
                        p1,
                        Set.of(a2, a3)
                )
        );
    }
}
