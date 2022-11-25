package cz.osu.itemrecordsbe.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByUserId(Long userId);

    AppUser findTopByOrderByUserIdDesc();

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);



}
