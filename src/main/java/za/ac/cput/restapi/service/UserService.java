package za.ac.cput.restapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import za.ac.cput.restapi.entity.Role;
import za.ac.cput.restapi.entity.User;
import za.ac.cput.restapi.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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
        user.setRoles(roleService.getByRoleNames(user.getRoles()));
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public User updateUser(User newUser, Long id)
    {
        return userRepository.findById(id).map(user ->
        {
            String firstName = newUser.getFirstName() != null ?
                    newUser.getFirstName() :
                    user.getFirstName();
            String lastName = newUser.getLastName() != null ?
                    newUser.getLastName() :
                    user.getLastName();
            String email = newUser.getEmail() != null ?
                    newUser.getEmail() :
                    user.getEmail();
            String password = newUser.getPassword() != null ?
                    newUser.getPassword() :
                    user.getPassword();
            Set<Role> roles = newUser.getRoles().isEmpty() ?
                    user.getRoles() :
                    newUser.getRoles();

            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword(password);
            user.setRoles(roleService.getByRoleNames(roles));
            user.setModifiedAt(LocalDateTime.now());

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
