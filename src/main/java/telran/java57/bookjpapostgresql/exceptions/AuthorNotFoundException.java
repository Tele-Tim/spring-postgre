package telran.java57.bookjpapostgresql.exceptions;

public class AuthorNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Author not found";

    public AuthorNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public AuthorNotFoundException(String message) {
        super(message);
    }


}
