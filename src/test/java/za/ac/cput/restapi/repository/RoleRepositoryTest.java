package za.ac.cput.restapi.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import za.ac.cput.restapi.entity.User.Role;
import za.ac.cput.restapi.repository.User.RoleRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RoleRepositoryTest
{
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TestEntityManager testEntityManager;

    Role roleAdmin = new Role("ADMIN");
    Role roleUser = new Role("USER");

    @Test
    public void saveRoleTest()
    {
        Role savedAdminRole = roleRepository.save(roleAdmin);
        Role savedUserRole = roleRepository.save(roleUser);

        Role existAdminRole = testEntityManager.find(Role.class, savedAdminRole.getId());
        Role existUserRole = testEntityManager.find(Role.class, savedUserRole.getId());

        assertThat(roleAdmin.getRoleName()).isEqualTo(existAdminRole.getRoleName());
        assertThat(roleUser.getRoleName()).isEqualTo(existUserRole.getRoleName());
    }

    @Test
    public void getRoleByIdTest()
    {
        Role existRole = roleRepository.findById(1L).orElseThrow();

        assertThat(existRole.getRoleName()).isEqualTo("ADMIN");
    }

    @Test
    public void getRolesTest()
    {
        List<Role> roles = roleRepository.findAll();

        assertThat(roles.size()).isGreaterThan(0);
    }

    @Test
    public void updateRoleTest()
    {
        Role existRole = roleRepository.findById(1L).orElseThrow();
        String existRoleName = existRole.getRoleName();

        existRole.setRoleName("OWNER");

        Role updateRole = roleRepository.save(existRole);

        assertThat(updateRole.getRoleName()).isNotEqualTo(existRoleName);
    }

    @Test
    public void deleteRoleTest()
    {
        Role existRole = roleRepository.findById(1L).orElseThrow();

        roleRepository.delete(existRole);

        boolean roleNotExists = roleRepository.findById(1L).isPresent();

        assertThat(roleNotExists).isFalse();
    }

}