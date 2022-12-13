package cz.osu.itemrecordsbe.repositories;

import cz.osu.itemrecordsbe.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findUserByUsername(String username);
    Optional<AppUser> findByUserId(Long id);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);


    AppUser findByUsername(String username);

    AppUser findTopByOrderByUserIdDesc();

    List<AppUser> findAppUsersByInterestGroupsGroupId(Long groupId);



}
