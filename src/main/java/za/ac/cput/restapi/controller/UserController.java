package za.ac.cput.restapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.restapi.entity.User;
import za.ac.cput.restapi.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController
{
    private UserService userService;

    @GetMapping
    public List<User> getAll()
    {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id)
    {
        return userService.getById(id);
    }

    @PostMapping
    public User addUser(@RequestBody User user)
    {
        return userService.addUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id)
    {
        return userService.updateUser(user, id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id)
    {
        userService.deleteUserById(id);
    }
}
