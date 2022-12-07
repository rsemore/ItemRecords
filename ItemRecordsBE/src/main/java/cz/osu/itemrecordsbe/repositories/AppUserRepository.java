package cz.osu.itemrecordsbe.repositories;

import cz.osu.itemrecordsbe.models.AppUser;
import cz.osu.itemrecordsbe.models.InterestGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByUserId(Long id);

    AppUser findByUsername(String username);

    AppUser findTopByOrderByUserIdDesc();

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    List<AppUser> findAppUsersByInterestGroupsGroupId(Long groupId);



}
