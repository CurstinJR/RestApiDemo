package za.ac.cput.restapi.controller.UserController;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.restapi.entity.User;
import za.ac.cput.restapi.service.UserService;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController
{
    private UserService userService;
    private UserModelAssembler userModelAssembler;

    @GetMapping
    public CollectionModel<EntityModel<User>> getAll()
    {
        return userModelAssembler.toCollectionModel(userService.getAll());
    }

    @GetMapping("/{id}")
    public EntityModel<User> getById(@PathVariable Long id)
    {
        return userModelAssembler.toModel(userService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user)
    {
        EntityModel<User> userEntityModel = userModelAssembler.toModel(userService.addUser(user));

        return ResponseEntity
                .created(userEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(userEntityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable Long id)
    {
        EntityModel<User> userEntityModel = userModelAssembler.toModel(userService.updateUser(user, id));

        return ResponseEntity
                .created(userEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(userEntityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id)
    {
        userService.deleteUserById(id);

        return ResponseEntity.noContent().build();
    }
}
