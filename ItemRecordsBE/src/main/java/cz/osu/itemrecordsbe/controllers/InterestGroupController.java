package cz.osu.itemrecordsbe.controllers;

import cz.osu.itemrecordsbe.services.InterestGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groups/")
@CrossOrigin
public class InterestGroupController {

    @Autowired
    private InterestGroupService groupService;

    @GetMapping("all")
    ResponseEntity<Object> getAllInterestGroups() {
        return groupService.getAllInterestGroups();
    }

    @PostMapping("{groupId}/user/{userId}")
    ResponseEntity<Object> addInterestToUser(@PathVariable("groupId") Long groupId, @PathVariable("userId") Long userId) {
        return groupService.addInterestToUser(groupId, userId);
    }

    @DeleteMapping("{groupId}/delete/user/{userId}")
    ResponseEntity<Object> removeUserFromGroup(@PathVariable("groupId") Long groupId, @PathVariable("userId") Long userId) {
        return groupService.removeInterestFromUser(groupId, userId);
    }

}
