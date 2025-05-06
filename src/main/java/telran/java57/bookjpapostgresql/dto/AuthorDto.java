package telran.java57.bookjpapostgresql.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
    String name;
    LocalDate birthDate;
}
