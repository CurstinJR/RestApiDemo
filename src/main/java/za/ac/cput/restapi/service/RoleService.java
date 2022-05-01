package za.ac.cput.restapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import za.ac.cput.restapi.entity.Role;
import za.ac.cput.restapi.repository.RoleRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoleService
{
    private RoleRepository roleRepository;

    public Set<Role> getAll()
    {
        return new HashSet<>(roleRepository.findAll());
    }

    public Role getById(Long id)
    {
        return roleRepository.getById(id);
    }

    public Set<Role> getByRoleNames(Set<Role> roles)
    {
        Set<String> roleNames = roles.stream()
                .map(Role::getRoleName)
                .collect(Collectors.toSet());
        return roleRepository.findByRoleNameIn(roleNames);
    }

    public Role addRole(Role role)
    {
        return roleRepository.save(role);
    }

    public void deleteRoleById(Long id)
    {
        roleRepository.deleteById(id);
    }

    public void deleteRoleByName(String roleName)
    {
        roleRepository.deleteByRoleName(roleName);
    }
}
