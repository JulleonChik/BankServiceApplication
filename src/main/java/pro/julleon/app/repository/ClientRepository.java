package pro.julleon.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pro.julleon.app.entity.Client;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByLogin(String login);

    Optional<Client> findByEmailsContains(String email);

    Optional<Client> findByPhonesContains(String phone);

    Page<Client> findByDateOfBirthIsGreaterThanEqual(LocalDate dateOfBirth, Pageable pageable);



    @Query("SELECT c FROM Client c WHERE " +
           "LOWER(c.firstName) LIKE LOWER(CONCAT(:text, '%')) OR " +
           "LOWER(c.middleName) LIKE LOWER(CONCAT(:text, '%')) OR " +
           "LOWER(c.lastName) LIKE LOWER(CONCAT(:text, '%')) OR " +
           "LOWER(CONCAT(c.firstName, ' ', c.middleName, ' ', c.lastName)) LIKE LOWER(CONCAT(:text, '%')) OR " +
           "LOWER(CONCAT(c.firstName, ' ', c.lastName, ' ', c.middleName)) LIKE LOWER(CONCAT(:text, '%')) OR " +
           "LOWER(CONCAT(c.middleName, ' ', c.firstName, ' ', c.lastName)) LIKE LOWER(CONCAT(:text, '%')) OR " +
           "LOWER(CONCAT(c.middleName, ' ', c.lastName, ' ', c.firstName)) LIKE LOWER(CONCAT(:text, '%')) OR " +
           "LOWER(CONCAT(c.lastName, ' ', c.firstName, ' ', c.middleName)) LIKE LOWER(CONCAT(:text, '%')) OR " +
           "LOWER(CONCAT(c.lastName, ' ', c.middleName, ' ', c.firstName)) LIKE LOWER(CONCAT(:text, '%'))")
    Page<Client> searchByFullName(@Param("text") String text, Pageable pageable);
}
