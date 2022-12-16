package cz.osu.itemrecordsbe.services;

import org.springframework.http.ResponseEntity;

public interface InterestGroupService {
    ResponseEntity<Object> getAllInterestGroups();

    ResponseEntity<Object> addInterestToUser(Long groupId, Long userId);

    ResponseEntity<Object> removeInterestFromUser(Long groupId, Long userId);
}
