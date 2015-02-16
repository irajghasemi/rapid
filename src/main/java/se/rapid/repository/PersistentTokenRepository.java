package se.rapid.repository;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.data.mongodb.repository.MongoRepository;

import se.rapid.domain.Customer;
import se.rapid.domain.PersistentToken;

/**
 * Spring Data MongoDB repository for the PersistentToken entity.
 */
public interface PersistentTokenRepository extends MongoRepository<PersistentToken, String> {

    List<PersistentToken> findByUser(Customer user);

    List<PersistentToken> findByTokenDateBefore(LocalDate localDate);

}
