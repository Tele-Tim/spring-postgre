package telran.java57.bookjpapostgresql.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode (of = "name")
public class Author {
    @Id
    String name;
    LocalDate birthDate;
}
