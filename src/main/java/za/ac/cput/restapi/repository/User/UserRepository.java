package za.ac.cput.restapi.repository.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.restapi.entity.User.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> findByFirstName(String firstName);

    Optional<User> findByLastName(String lastname);

    Optional<User> findByFirstNameAndLastName(String firstName, String lastname);

    Optional<User> findByEmail(String email);
}
