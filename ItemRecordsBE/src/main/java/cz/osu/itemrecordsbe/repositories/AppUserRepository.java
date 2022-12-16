package cz.osu.itemrecordsbe.repositories;

import cz.osu.itemrecordsbe.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findUserByUsername(String username);

    AppUser findByUserId(Long id);

    Boolean existsByUserId(Long userId);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
