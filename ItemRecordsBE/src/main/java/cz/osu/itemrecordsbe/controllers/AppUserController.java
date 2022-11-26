package cz.osu.itemrecordsbe.controllers;

import cz.osu.itemrecordsbe.models.AppUser;
import cz.osu.itemrecordsbe.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/")
@CrossOrigin
public class AppUserController {

    @Autowired
    private AppUserRepository appUserRepository;

    // Fill table with user data


    @PostMapping("login")
    ResponseEntity<?> loginUser(@RequestBody AppUser userData){
        AppUser user = appUserRepository.findByUsername(userData.getUsername());
        if(user.getPassword().equals(userData.getPassword()))
            return new ResponseEntity<>(user, HttpStatus.OK);
        return new ResponseEntity<>("Invalid username/password", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("all")
    ResponseEntity<List<AppUser>> getAll() {
        return ResponseEntity.ok(appUserRepository.findAll());
    }

    @GetMapping(path = "{userId}")
    ResponseEntity<AppUser> getUser(@PathVariable("userId") Long userId) {
        AppUser appUser = appUserRepository.findByUserId(userId);
        return new ResponseEntity<>(appUser, HttpStatus.OK);
    }

    @PostMapping(value = "add")
    ResponseEntity<String> addUser(@RequestBody AppUser user) {
        if (appUserRepository.existsByEmail(user.getEmail()))
            return new ResponseEntity<>("Email already registered", HttpStatus.BAD_REQUEST);
        if (appUserRepository.existsByUsername(user.getUsername()))
            return new ResponseEntity<>("Username already taken", HttpStatus.BAD_REQUEST);
        else {
            Long lastId = appUserRepository.findTopByOrderByUserIdDesc().getUserId();
            lastId++;
            user.setUserId(lastId);
            appUserRepository.save(user);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
    }

}
