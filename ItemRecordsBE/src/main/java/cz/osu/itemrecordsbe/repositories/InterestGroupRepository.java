package cz.osu.itemrecordsbe.repositories;

import cz.osu.itemrecordsbe.models.InterestGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterestGroupRepository extends JpaRepository<InterestGroup, Long> {

    InterestGroup findByGroupId(Long id);

    List<InterestGroup> findInterestGroupsByAppUsersUserId(Long userId);

}
