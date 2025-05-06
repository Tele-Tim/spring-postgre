package telran.java57.bookjpapostgresql.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode(of = "publisherName")
public class Publisher {
    @Id
    String publisherName;
}
