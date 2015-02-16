package se.rapid.repository;

import java.util.List;
import java.util.Optional;

import org.joda.time.DateTime;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import se.rapid.domain.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    Optional<Customer> findOneByActivationKey(String activationKey);

    List<Customer> findAllByActivatedIsFalseAndCreatedDateBefore(DateTime dateTime);

    Optional<Customer> findOneByEmail(String email);

    Optional<Customer> findOneByLogin(String login);

    void delete(Customer t);

}
