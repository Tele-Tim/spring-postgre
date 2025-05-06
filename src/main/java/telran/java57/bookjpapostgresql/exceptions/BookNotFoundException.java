package telran.java57.bookjpapostgresql.exceptions;

public class BookNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Book not found";

    public BookNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public BookNotFoundException(String message) {
        super(message);
    }
}
