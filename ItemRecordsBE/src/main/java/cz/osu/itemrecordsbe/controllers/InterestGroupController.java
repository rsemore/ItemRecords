package cz.osu.itemrecordsbe.controllers;

import cz.osu.itemrecordsbe.models.AppUser;
import cz.osu.itemrecordsbe.models.InterestGroup;
import cz.osu.itemrecordsbe.repositories.AppUserRepository;
import cz.osu.itemrecordsbe.repositories.InterestGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/groups/")
@CrossOrigin(origins = "http://localhost:4200/")
public class InterestGroupController {

    @Autowired
    private InterestGroupRepository interestGroupRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @GetMapping("all")
    ResponseEntity<List<InterestGroup>> getAllInterestGroups() {
        List<InterestGroup> groups = interestGroupRepository.findAll();
        for (InterestGroup group : groups) {
            group.setAppUsers(null);
        }
        return ResponseEntity.ok(groups);
    }

    @PostMapping("{groupId}/user/{userId}")
    ResponseEntity<String> addUserToGroup(@PathVariable("groupId") Long groupId, @PathVariable("userId") Long userId) {
        InterestGroup group = interestGroupRepository.findByGroupId(groupId);
        AppUser user = appUserRepository.findByUserId(userId);
        user.addInterestGroup(group);
        appUserRepository.save(user);
        return ResponseEntity.ok("Interest group added to user");
    }

}
