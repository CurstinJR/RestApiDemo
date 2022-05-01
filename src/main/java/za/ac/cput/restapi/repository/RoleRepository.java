package za.ac.cput.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.restapi.entity.Role;

import java.util.Collection;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>
{
    Set<Role> findByRoleNameIn(Collection<String> roleName);
    Role findByRoleName(String roleName);
    void deleteByRoleName(String roleName);
}
