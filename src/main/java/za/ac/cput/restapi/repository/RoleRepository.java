package za.ac.cput.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.cput.restapi.entity.Role;

import java.util.Collection;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>
{
    @Query("SELECT r from roles r WHERE r.roleName IN (:roleName)")
    Set<Role> findByRoleNameIn(String... roleName);
    Role findByRoleName(String roleName);
}
