package cz.osu.itemrecordsbe.services.impls;

import cz.osu.itemrecordsbe.models.AppUser;
import cz.osu.itemrecordsbe.models.InterestGroup;
import cz.osu.itemrecordsbe.repositories.InterestGroupRepository;
import cz.osu.itemrecordsbe.services.AppUserService;
import cz.osu.itemrecordsbe.services.InterestGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterestGroupServiceImpl implements InterestGroupService {

    @Autowired
    private InterestGroupRepository groupRepository;

    @Autowired
    private AppUserService userService;

    @Override
    public ResponseEntity<Object> getAllInterestGroups() {
        List<InterestGroup> ret = groupRepository.findAll();
        ret.forEach(interestGroup -> interestGroup.setAppUsers(null));
        return ResponseEntity.ok(ret);
    }

    @Override
    public ResponseEntity<Object> addInterestToUser(Long groupId, Long userId) {
        if (!userService.existsByUserId(userId))
            return ResponseEntity.notFound().build();
        if (!groupRepository.existsById(groupId))
            return ResponseEntity.notFound().build();

        InterestGroup group = groupRepository.findByGroupId(groupId);
        AppUser user = userService.getUserById(userId);
        user.addInterestGroup(group);
        userService.saveUser(user);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> removeInterestFromUser(Long groupId, Long userId) {
        if (!userService.existsByUserId(userId))
            return ResponseEntity.notFound().build();
        if (!groupRepository.existsById(groupId))
            return ResponseEntity.notFound().build();

        InterestGroup group = groupRepository.findByGroupId(groupId);
        AppUser user = userService.getUserById(userId);
        group.getAppUsers().remove(user);
        user.getInterestGroups().remove(group);
        userService.saveUser(user);
        groupRepository.save(group);

        return ResponseEntity.ok().build();
    }
}
