package telran.java57.bookjpapostgresql.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode (of = "isbn")
public class Book {
   @Id
    String isbn;
    String title;
    @ManyToOne
    Publisher publisher;
    @ManyToMany
    Set<Author> authors;

}
