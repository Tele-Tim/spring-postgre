package telran.java57.bookjpapostgresql.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import telran.java57.bookjpapostgresql.model.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, String> {
}
