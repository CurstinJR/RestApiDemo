package za.ac.cput.restapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import za.ac.cput.restapi.entity.User;
import za.ac.cput.restapi.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService
{
    private UserRepository userRepository;

    public List<User> getAll()
    {
        return userRepository.findAll();
    }

    public User getById(Long id)
    {
        return userRepository.findById(id).orElseThrow();
    }

    public User getByFullName(String firstName, String lastName)
    {
        return userRepository
                .findByFirstNameAndLastName(firstName, lastName)
                .orElseThrow();
    }
}
