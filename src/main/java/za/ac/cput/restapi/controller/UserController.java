package za.ac.cput.restapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.restapi.entity.User;
import za.ac.cput.restapi.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController
{
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAll()
    {
        return userService.getAll();
    }

    @GetMapping("/user/{id}")
    public User getById(@PathVariable Long id)
    {
        return userService.getById(id);
    }

    @GetMapping("/user")
    public User getByFullName(@RequestParam String firstName,
                              @RequestParam String lastName)
    {
        return userService.getByFullName(firstName, lastName);
    }
}
