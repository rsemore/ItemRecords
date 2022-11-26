package cz.osu.itemrecordsbe.repositories;

import cz.osu.itemrecordsbe.models.ERole;
import cz.osu.itemrecordsbe.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);



}
