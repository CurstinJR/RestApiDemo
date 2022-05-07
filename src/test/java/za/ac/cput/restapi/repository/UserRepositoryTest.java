package za.ac.cput.restapi.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import za.ac.cput.restapi.entity.User.User;
import za.ac.cput.restapi.repository.User.UserRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    TestEntityManager testEntityManager;

    User user = User.builder()
            .firstName("Joe")
            .lastName("Caputo")
            .email("joe@email.com")
            .password("joe@123")
            .build();

    @Test
    public void saveUserTest()
    {
        User savedUser = userRepository.save(user);

        User existUser = testEntityManager.find(User.class, savedUser.getId());

        assertThat(user.getId()).isEqualTo(existUser.getId());
    }

    @Test
    public void getUserByIdTest()
    {
        User existUser = userRepository.findById(1L).orElseThrow();

        assertThat(existUser.getEmail()).isEqualTo("quinn@email.com");
    }

    @Test
    public void getUsersTest()
    {
        List<User> users = userRepository.findAll();

        assertThat(users.size()).isGreaterThan(0);
    }

    @Test
    public void updateUserTest()
    {
        User existUser = userRepository.findById(1L).orElseThrow();
        String existUserEmail = existUser.getEmail();

        existUser.setEmail("quinn123@email.com");

        User updateUser = userRepository.save(existUser);

        assertThat(updateUser.getEmail()).isNotEqualTo(existUserEmail);
    }

    @Test
    public void deleteUserTest()
    {
        User existUser = userRepository.findById(1L).orElseThrow();

        userRepository.delete(existUser);

        boolean userNotExists = userRepository.findById(1L).isPresent();

        assertThat(userNotExists).isFalse();
    }

}