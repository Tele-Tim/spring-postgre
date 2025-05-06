package telran.java57.bookjpapostgresql.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    String isbn;
    String title;
    PublisherDto publisher;
    Set<AuthorDto> authors;

}
