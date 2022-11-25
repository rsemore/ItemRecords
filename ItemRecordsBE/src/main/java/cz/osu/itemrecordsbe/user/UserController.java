package cz.osu.itemrecordsbe.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/")
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("all")
    ResponseEntity<List<AppUser>> getAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping(path = "{userId}")
    ResponseEntity<AppUser> getUser(@PathVariable("userId") Long userId) {
        AppUser appUser = userRepository.findByUserId(userId);
        return new ResponseEntity(appUser, HttpStatus.OK);
    }

    @PostMapping(value = "add")
    ResponseEntity<String> addUser(@RequestBody AppUser user){
        Long lastId = userRepository.findTopByOrderByUserIdDesc().getUserId();
        lastId++;
        user.setUserId(lastId);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

}
