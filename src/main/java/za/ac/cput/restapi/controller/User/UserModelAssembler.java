package za.ac.cput.restapi.controller.User;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import za.ac.cput.restapi.entity.User.User;

import java.util.List;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>>
{
    @Override
    public EntityModel<User> toModel(User user)
    {
        return EntityModel.of(user,
                linkTo(methodOn(UserController.class).getById(user.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).getAll()).withRel("users"));
    }

    @Override
    public CollectionModel<EntityModel<User>> toCollectionModel(Iterable<? extends User> entities)
    {
        List<EntityModel<User>> users = StreamSupport
                .stream(entities.spliterator(), false)
                .map(this::toModel)
                .toList();
        return CollectionModel.of(users,
                linkTo(methodOn(UserController.class).getAll()).withSelfRel());
    }
}
