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
    private RoleService roleService;

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

    public User addUser(User user)
    {
        return userRepository.save(user);
    }

    public User updateUser(User newUser, Long id)
    {
        return userRepository.findById(id).map(user ->
        {
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setEmail(newUser.getEmail());
            user.setPassword(newUser.getPassword());
            user.setRoles(roleService.getByRoleNames(newUser.getRoles()));
            return userRepository.save(user);
        }).orElseGet(() ->
        {
            newUser.setId(id);
            return userRepository.save(newUser);
        });
    }

    public void deleteUserById(Long id)
    {
        userRepository.deleteById(id);
    }
}
