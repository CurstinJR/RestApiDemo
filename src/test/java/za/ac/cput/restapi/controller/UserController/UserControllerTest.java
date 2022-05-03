package za.ac.cput.restapi.controller.UserController;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import za.ac.cput.restapi.controller.User.UserController;
import za.ac.cput.restapi.controller.User.UserModelAssembler;
import za.ac.cput.restapi.entity.User.Role;
import za.ac.cput.restapi.entity.User.User;
import za.ac.cput.restapi.repository.User.RoleRepository;
import za.ac.cput.restapi.repository.User.UserRepository;
import za.ac.cput.restapi.service.User.RoleService;
import za.ac.cput.restapi.service.User.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({
        UserController.class,
        UserService.class,
        RoleService.class,
        UserModelAssembler.class
})
class UserControllerTest
{
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    UserRepository userRepository;
    @MockBean
    RoleRepository roleRepository;

    User user1 = User.builder()
            .id(1L)
            .firstName("Quinn")
            .lastName("Frost")
            .email("quinn@email.com")
            .password("password@123")
            .createdAt(LocalDateTime.now())
            .roles(Set.of(new Role(1L, "ADMIN"), new Role(2L, "USER")))
            .build();

    User user2 = User.builder()
            .id(2L)
            .firstName("Jack")
            .lastName("Renewed")
            .email("jack@email.com")
            .password("password@456")
            .createdAt(LocalDateTime.now())
            .roles(Set.of(new Role(2L, "USER")))
            .build();

    User user3 = User.builder()
            .id(3L)
            .firstName("Alyssa")
            .lastName("Tessia")
            .email("alyssa@email.com")
            .password("password@789")
            .createdAt(LocalDateTime.now())
            .roles(Set.of(new Role(2L, "USER")))
            .build();

    @Test
    void getAll() throws Exception
    {
        List<User> users = List.of(
                user1,
                user2,
                user3
        );

        when(userRepository.findAll()).thenReturn(users);

        mockMvc.perform(get("/api/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$._embedded.userList", hasSize(3)))
                .andExpect(jsonPath("$._embedded.userList[2].email", is(user3.getEmail())))
                .andExpect(status().isOk());
    }

    @Test
    void getById() throws Exception
    {
        when(userRepository.findById(user1.getId()))
                .thenReturn(Optional.of(user1));

        mockMvc.perform(get("/api/users/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.email", is(user1.getEmail())))
                .andExpect(status().isOk());
    }

    @Test
    void addUser() throws Exception
    {
        User user = User.builder()
                .id(4L)
                .firstName("Jessie")
                .lastName("Mixy")
                .email("jess@email.com")
                .password("jess@123")
                .roles(roleRepository.findByRoleNameIn(Set.of("USER")))
                .createdAt(LocalDateTime.now())
                .build();

        when(userRepository.save(user)).thenReturn(user);

        MockHttpServletRequestBuilder mockRequest = post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user));

        mockMvc.perform(mockRequest)
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.email", is(user.getEmail())));
    }

    @Test
    void updateUser() throws Exception
    {
        User user = User.builder()
                .id(1L)
                .email("quinn@gmail.com")
                .password("quinn@123")
                .roles(user1.getRoles())
                .build();

        when(userRepository.findById(user1.getId()))
                .thenReturn(Optional.of(user1));
        when(userRepository.save(user)).thenReturn(user);

        MockHttpServletRequestBuilder mockRequest = put("/api/users/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user));

        mockMvc.perform(mockRequest)
                .andDo(print())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.email", is(user.getEmail())))
                .andExpect(status().isCreated());

    }

    @Test
    void deleteUser() throws Exception
    {
        when(userRepository.findById(11L))
                .thenReturn(null);

        mockMvc.perform(delete("/api/users/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}